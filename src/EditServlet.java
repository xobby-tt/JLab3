import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            MyObject object = Storage.readById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("object", object);
        } catch(NumberFormatException e) {
        }
        HashMap<String, Boolean> errorsView = new HashMap<String, Boolean>();
        errorsView.put("emptyAuthor", false);
        errorsView.put("emptyName", false);
        errorsView.put("wrongDuration", false);


        req.setAttribute("errorsView", errorsView);

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }
}