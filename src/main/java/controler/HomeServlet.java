package controler;

import service.StatusDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet",value = "/home")
public class HomeServlet extends HttpServlet {
    StatusDAOImpl statusDAO;

    @Override
    public void init() throws ServletException {
        statusDAO = new StatusDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "findStatus":

                break;
        }
    }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String actionGet = req.getParameter("actionGet");
            if (actionGet == null) {
                actionGet = "";
            }
            switch (actionGet) {
                default:
            }
        }

    }

