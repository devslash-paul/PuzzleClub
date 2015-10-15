package servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import puzzles.ExamMark;
import puzzles.TestRunner;

/**
 * User: pault Date: 9/10/2015 Time: 10:37
 */
@WebServlet("/submit")
@MultipartConfig
public class SumissionServlet extends HttpServlet
{
    @Override
    protected void doPost( final HttpServletRequest req, final HttpServletResponse resp )
        throws ServletException, IOException
    {
        final File result = saveFile( "uploads", req );
        ExamMark results = null;

        try
        {
            results = TestRunner
                .runTest( req.getParameter( "puzzle" ), result, this.getClass().getClassLoader(), resp );
            if(results == null)
                return;
            results.setName(req.getParameter("name"));

            lookOrUpdate(req.getParameter("puzzle"), results);

        }
        catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | InterruptedException e )
        {
            e.printStackTrace();
        }

        req.setAttribute( "results", results );
        req.getRequestDispatcher( "/results.jsp" ).forward( req, resp );

    }

    private void lookOrUpdate(String puzzle, ExamMark results) throws IOException, ClassNotFoundException {
        new File("uploads" + File.separator + "puzzle").mkdirs();
        File namePuzzle = new File("uploads" + File.separator + puzzle + File.separator + results.getName() + ".res");

        ExamMark already = null;
        if (namePuzzle.exists()) {
            FileInputStream inStream = new FileInputStream(namePuzzle);
            ObjectInputStream objStream = new ObjectInputStream(inStream);
            already = (ExamMark) objStream.readObject();
            objStream.close();
            inStream.close();

        }

        if (already == null || already.compareTo(results) < 0) {
            // Then we should write this object
            FileOutputStream outStream = new FileOutputStream(namePuzzle);
            ObjectOutputStream objStream = new ObjectOutputStream(outStream);
            objStream.writeObject(results);
            objStream.close();
            outStream.close();
        }
    }

    private File saveFile( final String path, final HttpServletRequest req ) throws IOException, ServletException
    {
        Part filePart = req.getPart( "file" );
        final String fileName = getFileName( filePart );

        try( OutputStream out = new FileOutputStream( new File( path + File.separator + fileName ) ); )
        {

            final InputStream filecontent = filePart.getInputStream();
            int read;

            final byte[] bytes = new byte[1024];

            while ( ( read = filecontent.read( bytes ) ) != -1 )
            {
                out.write( bytes, 0, read );
            }
        }
        return new File( path + File.separator + fileName );
    }

    private String getFileName(final Part part)
    {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) )
        {
            if ( content.trim().startsWith( "filename" ) )
            {
                return content.substring(
                    content.indexOf( "=" ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
}
