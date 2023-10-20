package controler;

import model.User;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("actionGet");
        if (actionGet == null) {
            actionGet = "";
        }
        switch (actionGet) {
            case "directToProfile":
                break;
            default:
                directToProfile(req, resp);

        }
    }

    private void directToProfile(HttpServletRequest req, HttpServletResponse resp) {
        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            User userNeedToEdit = userDAO.getUserById(1);
            req.setAttribute("userNeedToEdit", userNeedToEdit);
            req.getRequestDispatcher("/User/user-profile.jsp").include(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost = req.getParameter("actionPost");
        if (actionPost == null) {
            actionPost = "";
        }
        switch (actionPost) {
            case "updateUser":
                updateUser(req, resp);
            default:
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String birthdate = req.getParameter("birthdate");
        String avatar = req.getParameter("avatar");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");

        User userAfterEdit = new User();
        userAfterEdit.setId(id);
        userAfterEdit.setUsername(username);
        userAfterEdit.setPassword(password);
        userAfterEdit.setEmail(email);
        userAfterEdit.setPhone(phone);
        userAfterEdit.setBirthdate(birthdate);
        userAfterEdit.setAvatar(avatar);
        userAfterEdit.setName(name);
        userAfterEdit.setAddress(address);
        userAfterEdit.setHobby(hobby);

        System.out.println(userAfterEdit);

        // code thay doi csdl o day

        resp.sendRedirect("/user");
    }
}

