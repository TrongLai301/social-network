package controler;

import model.Status;
import model.User;
import service.StatusDAOImpl;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet",value = "/home")
public class HomeServlet extends HttpServlet {
    StatusDAOImpl statusDAO;
    UserDAOImpl userDAO;

    @Override
    public void init() throws ServletException {
        statusDAO = new StatusDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "findStatus":
                  findStatusByName(req,resp);
                break;
            case "":
                break;
        }
    }
    public void findStatusByName(HttpServletRequest request ,HttpServletResponse response){
        String searchContent = request.getParameter("searchContent");
        String option = request.getParameter("option");
        try {
            List<Status> statusList = statusDAO.findStatus(searchContent,option);
            request.setAttribute("listStatusFindBySearch",statusList);
            request.getRequestDispatcher("display-home/homeFB.jsp").forward(request,response);
        } catch (SQLException | ClassNotFoundException | ServletException | IOException e) {
            throw new RuntimeException(e);
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

