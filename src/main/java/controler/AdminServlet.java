package controler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*  Xử lý logic liên quan đến trang Admin */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet =  req.getParameter("actionGet");
        if (actionGet == null){
            actionGet = "";
        }
        switch (actionGet) {
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost =  req.getParameter("actionPost");
        if (actionPost == null){
            actionPost = "";
        }
        switch (actionPost) {
            default:
        }
    }
}
