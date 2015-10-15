package servlet;

import puzzles.ExamMark;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pault on 15/10/15.
 */
@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puzzle = req.getParameter("puzzle");
        File uploads = new File("uploads" + File.separator + puzzle);
        List<String> abc = new ArrayList<>();

        for (File op : uploads.listFiles()) {
            ExamMark mark = getMark(op);
            if (mark != null) {
                abc.add(mark.toHTMLString());
            }
        }

        PrintWriter writer = resp.getWriter();

        for (String line : abc) {
            writer.write(line + "<br/>");
        }
        writer.close();

    }

    private ExamMark getMark(File op) {
        try {
            FileInputStream inStream = new FileInputStream(op);
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            return (ExamMark) objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
