package puzzles;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.sun.deploy.net.HttpResponse;
import puzzles.lexstring.LexMaxMarker;
import puzzles.lexstring.LexMaxPuzzle;
import puzzles.sortstring2.LongStringMarker;
import puzzles.sortstring2.LongStringPuzzle;

import javax.servlet.http.HttpServletResponse;

/**
 * User: pault Date: 9/10/2015 Time: 11:07
 */
public class TestRunner
{
    public static ExamMark runTest( final String puzzle, final File file, ClassLoader loader, HttpServletResponse resp)
        throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException,
               InterruptedException
    {
        String fileToCompile = file.getAbsolutePath();
        final String javaClass = file.getName().replace( ".java", "" );

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec( "javac -cp /home/pault/Programming/Java/Marker/src/ " + fileToCompile );

        pr.waitFor();

        Scanner error = new Scanner(pr.getErrorStream());
        System.out.println("__________________________________");
        boolean errorBool = false;
        while (error.hasNext()) {
            errorBool = true;
            resp.getWriter().println(error.nextLine());
        }
        if (errorBool) {
            return null;
        }
        Scanner out = new Scanner(pr.getInputStream());
        while (out.hasNext()) {
            System.out.println(out.nextLine());
        }
        System.out.println("__________________________________");

        Object loaded = PuzzleFactory.newInstance( javaClass, loader );

        switch ( puzzle )
        {
            case "longString":
                LongStringMarker ssMarker = new LongStringMarker();
                return ssMarker.mark( (LongStringPuzzle)loaded );
            case "lexStrings":
                LexMaxMarker lexMarker = new LexMaxMarker();
                return lexMarker.mark( (LexMaxPuzzle)loaded );
        }

        return null;
    }
}
