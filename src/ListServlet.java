import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Collection<MyObject> objects = Storage.readAll();

        req.setAttribute("objects", objects);
        req.setAttribute("summaryDuration", Storage.getSummaryDuration());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

    }
}
