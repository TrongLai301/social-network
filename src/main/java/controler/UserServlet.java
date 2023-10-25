package controler;

import model.User;
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
                    showUserProfile(req, resp);
                    break;
                default:
                    showHomePageForUser(req, resp);
                    break;
            }
        } catch (Exception e) {
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
        try {
            switch (actionPost) {
                case "editPassword":
                    editPassword(req, resp);
                    break;
                case "updateUser":
                    updateUser(req, resp);
                    break;
                default:
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void editPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        int idAccount = Integer.parseInt(req.getParameter("idAccount"));
        User user = userDAO.getUserById(idAccount);
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
        }
    }

                // Chuc nang update user profile va hien thi userProfile
                private void showUserProfile (HttpServletRequest req, HttpServletResponse resp){
                    try {
                        HttpSession session = req.getSession();
                        Integer idUser = (Integer) session.getAttribute("idAccount");
                        User userNeedToEdit = userDAO.getUserById(idUser);
                        req.setAttribute("userNeedToEdit", userNeedToEdit);
                        req.getRequestDispatcher("/user/userProfile/profile-view.jsp").forward(req, resp);
                    } catch (ServletException | IOException | SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                //Do post
                private void updateUser (HttpServletRequest req, HttpServletResponse resp) throws
                IOException, ServletException
                {
                    User userAfterEdit = new User();

                    int id = Integer.parseInt(req.getParameter("id"));
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String phone = req.getParameter("phone");
                    String date = req.getParameter("birth");
                    System.out.println(date);
                    System.out.println("".compareTo(date));
                    if (date != null && !date.isEmpty()){
                        LocalDate birth = LocalDate.parse(req.getParameter("birth"));
                        userAfterEdit.setBirth(birth);
                    }
                    String avatar = req.getParameter("avatar");
                    String name = req.getParameter("name");
                    String address = req.getParameter("address");
                    String hobby = req.getParameter("hobby");

                    // Cài avarta mặc định nếu người dùng không thêm avatar
                    if (avatar == null || avatar.isEmpty()) {
                        avatar = "https://facebookninja.vn/wp-content/uploads/2023/06/anh-dai-dien-mac-dinh-zalo.jpg";
                    }

                    userAfterEdit.setId(id);
                    userAfterEdit.setUsername(username);
                    userAfterEdit.setPassword(password);
                    userAfterEdit.setEmail(email);
                    userAfterEdit.setPhone(phone);
                    userAfterEdit.setAvatar(avatar);
                    userAfterEdit.setName(name);
                    userAfterEdit.setAddress(address);
                    userAfterEdit.setHobby(hobby);

                    User userToCheckEmailExit = userDAO.findUserWithEmailOrPhone(email,phone);
                    if (userToCheckEmailExit.getId() != userAfterEdit.getId()) {
                        if (userToCheckEmailExit.getId() != 0){
                            req.setAttribute("message", "Email hoặc số điện thoại đã tồn tại !");
                            req.setAttribute("userNeedToEdit", userAfterEdit);
                            req.getRequestDispatcher("/user/userProfile/profile-view.jsp").forward(req, resp);
                        }
                    }
                    userDAO.updateUser(userAfterEdit);
                    req.setAttribute("message", "Thêm Thành Công !");
                    req.setAttribute("userNeedToEdit", userAfterEdit);
                    req.getRequestDispatcher("/user/userProfile/profile-view.jsp").forward(req, resp);
                }

                //doGet
                private void showHomePageForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
                    req.getRequestDispatcher("user/home.jsp").forward(req,resp);
                }

            }



