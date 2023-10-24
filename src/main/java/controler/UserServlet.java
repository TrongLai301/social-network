package controler;

import model.User;
import service.IUserDAO;
import service.UserDAOImpl;
import service.Validate.PasswordValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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
                case "showUserProfile":
                    showUserProfile(req,resp);
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
                try {
                    editPassword(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "updateUser":
                updateUser(req,resp);
                break;
            default:
        }
    }

    private void editPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        int idAccount = Integer.parseInt(req.getParameter("idAccount"));
        User user = userDAO.getUserByIdForBlockandChange(idAccount);
if (password.equals(user.getPassword())) {
    if (!passwordValidate.validate(newPassword)) {
        req.setAttribute("message", "Vui long nhap tu 6-32 ky tu");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
    } else if (!newPassword.equals(confirmPassword)) {
        req.setAttribute("message", "Mat khau khong trung khop");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
    } else if (password.isEmpty()) {
        req.setAttribute("message", "Mat khau khong duoc trong");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
    } else {
        try {
            userDAO.editPasswordUser(idAccount, newPassword);
            req.setAttribute("message", "Doi mat khau thanh cong");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}else{
    req.setAttribute("message", "Mat khau cu khong trung khop");
    RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
    dispatcher.forward(req, resp);
}
    }
    // Chuc nang update user
    private void showUserProfile(HttpServletRequest req, HttpServletResponse resp){
        try {
            HttpSession session = req.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User userNeedToEdit = userDAO.getUserById(idUser);
            req.setAttribute("userNeedToEdit",userNeedToEdit);
            req.getRequestDispatcher("/user/userProfile/profile-view.jsp").forward(req,resp);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //Do post
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        LocalDate birth = LocalDate.parse(req.getParameter("birth"));
        String avatar = req.getParameter("avatar");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");

        if (avatar == null || avatar.isEmpty()){
            avatar = "https://facebookninja.vn/wp-content/uploads/2023/06/anh-dai-dien-mac-dinh-zalo.jpg";
        }

        User userAfterEdit = new User();
        userAfterEdit.setId(id);
        userAfterEdit.setUsername(username);
        userAfterEdit.setPassword(password);
        userAfterEdit.setEmail(email);
        userAfterEdit.setPhone(phone);
        userAfterEdit.setBirth(birth);
        userAfterEdit.setAvatar(avatar);
        userAfterEdit.setName(name);
        userAfterEdit.setAddress(address);
        userAfterEdit.setHobby(hobby);

        System.out.println(userAfterEdit);
        // code thay doi csdl o day
        userDAO.updateUser(userAfterEdit);
        req.setAttribute("actionGet","showUserProfile");
        resp.sendRedirect("/user");
    }

    //doGet
    private void showHomePageForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("user/home.jsp");
    }

    //doPost
    public void blockUserById(HttpServletRequest request, HttpServletResponse response) {

    }
}


