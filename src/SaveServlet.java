import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        boolean flag = false;
        MyObject object = new MyObject();
        HashMap<String, Boolean> errorsView = new HashMap<String, Boolean>();
        errorsView.put("emptyAuthor", false);
        errorsView.put("emptyName", false);
        errorsView.put("wrongDuration", false);



        object.setAuthor(req.getParameter("author-data"));
        object.setName(req.getParameter("name-data"));

        if (object.getAuthor().trim().length() == 0) {
            errorsView.replace("emptyAuthor", true);
            flag = true;
        }

        if (object.getName().trim().length() == 0) {
            errorsView.replace("emptyName", true);
            flag = true;
        }

        String durate[] = req.getParameter("duration-data").split(":");// ======to do(шаблон) =======
        DurationTime duration = new DurationTime();

        if (durate.length > 3 || durate.length < 2) {
            errorsView.replace("wrongDuration", true);
            flag = true;
        }

        if (durate.length == 3) {
            try {
                duration.setTwoParam(false);
                duration.setHours(Integer.parseInt(durate[durate.length - 3]));
            } catch (NumberFormatException e) {
                errorsView.replace("wrongDuration", true);
                flag = true;
            }
        }

        try{
            duration.setMinutes(Integer.parseInt(durate[durate.length - 2]));
            duration.setSeconds(Integer.parseInt(durate[durate.length - 1]));
            object.setDuration(duration);//====to do (маска ввода)===
            errorsView.replace("wrongDuration", !duration.correctTime());
        } catch (Exception e) {
            errorsView.replace("wrongDuration", true);
            flag = true;
        }

        try {
            object.setPublication(format1.parse(format1.format( new Date() )));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            object.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}

        if(!errorsView.get("wrongDuration") && !errorsView.get("emptyName") && !errorsView.get("emptyAuthor")) {
            if (object.getId() == null) {
                Storage.create(object);
            } else {
                Storage.update(object);
            }
        }


        if (flag) {
            redirect(req, resp, errorsView, object);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, HashMap flag, MyObject object) throws ServletException, IOException {
        req.setAttribute("errorsView",flag);
        req.setAttribute("object", object);
        req.setAttribute("summaryDuration", Storage.getSummaryDuration());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }
}