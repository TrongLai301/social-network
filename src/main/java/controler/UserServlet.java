package controler;

import service.IUserDAO;
import service.UserDAOImpl;
import service.Validate.PasswordValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    PasswordValidate passwordValidate;
    UserDAOImpl userDAO;

    @Override
    public void init() throws ServletException {
        passwordValidate = new PasswordValidate();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("actionGet");
        if (actionGet == null) {
            actionGet = "";
        }
        try {
            switch (actionGet) {
                case "showEditPassword":
                    showEditPassword(req, resp);
                    break;
                default:
                    showHomePageForUser(req, resp);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showEditPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("actionGet");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
        switch (actionGet) {
            case "":
                break;
            default:
                viewUserMain(req, resp);
                break;

        }
    }

    public void viewUserMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost = req.getParameter("actionPost");
        if (actionPost == null) {
            actionPost = "";
        }
        switch (actionPost) {
            case "block":
                blockUserById(req, resp);
                break;
            case "editPassword":
                editPassword(req, resp);
                break;
            default:
        }
    }

    private void editPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password= req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        int idAccount = Integer.parseInt(req.getParameter("idAccount"));

        if (passwordValidate.validate(newPassword) == false){
            req.setAttribute("message", "Vui long nhap tu 6-32 ky tu");
        }
        if (!newPassword.equals(confirmPassword)){
            req.setAttribute("message", "Mat khau khong trung khop");
        }
        if (password.isEmpty()){
            req.setAttribute("message", "Mat khau khong duoc trong");
        }
        try {
            userDAO.editPasswordUser(idAccount, newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("message", "Doi mat khau thanh cong");

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
    }

    //doGet
    private void showHomePageForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("user/home.jsp");
    }

    //doPost
    public void blockUserById(HttpServletRequest request, HttpServletResponse response) {


    }
}


