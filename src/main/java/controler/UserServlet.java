package controler;

import model.Status;
import model.User;
import service.UserDAOImpl;
import service.Validate.PasswordValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet(name = "UserServlet", value = "/user")
@MultipartConfig
public class UserServlet extends HttpServlet {
    PasswordValidate passwordValidate;
    UserDAOImpl userDAO;
    private static final String IMG_DIR = "/WEB-INF/img"; // Đường dẫn đến thư mục lưu trữ ảnh và video đính kèm

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
                case "updateUserProfile":
                    EditUserProfile(req,resp);
                    break;
                case "showUploadNewStatusForm":
                    showUploadNewStatusForm(req, resp);
                    break;
                default:
                    showHomePageForUser(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showUploadNewStatusForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/upNewStatus.jsp").forward(request, response);
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
                case "uploadNewStatus":
                    uploadNewStatus(req, resp);
                    break;
                default:
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadNewStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("idAccount");

        if (Objects.isNull(userID)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bạn chưa đăng nhập");
            return;
        }

        String description = request.getParameter("description");
        Part mediaPart = request.getPart("media");
        int permission = Integer.parseInt(request.getParameter("permission"));

        String mediaPath = null;
        if (mediaPart != null && mediaPart.getSize() > 0) {
            String fileName = LocalDateTime.now().toString().replace(':', '-') + ".png";
            Path uploadPath = Paths.get(getServletContext().getRealPath(IMG_DIR), fileName);
            try (InputStream inputStream = mediaPart.getInputStream()) {
                Files.copy(inputStream, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            }
            mediaPath = request.getContextPath() + IMG_DIR + "/" + fileName;
        }

        // Lưu thông tin trạng thái vào CSDL
        Status newStatus = new Status(description, userID, LocalDate.now(), mediaPath, permission);
        userDAO.insertStatus(newStatus);

        request.setAttribute("messagePost","upload success");
        request.getRequestDispatcher("user/home.jsp").forward(request,response);
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
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Chuc nang update user profile va hien thi userProfile
    private void EditUserProfile (HttpServletRequest req, HttpServletResponse resp){
        try {
            HttpSession session = req.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User userEdit = userDAO.getUserById(idUser);
            req.setAttribute("userNeedToEdit",userEdit);
            req.getRequestDispatcher("/user/userProfile/profile-view.jsp").forward(req, resp);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void showUserProfile (HttpServletRequest req, HttpServletResponse resp){
        try {
            HttpSession session = req.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User user = userDAO.getUserById(idUser);
            req.setAttribute("userProfile",user);
            req.getRequestDispatcher("/user/userProfile/displayProfile/homeFB.jsp").forward(req, resp);
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
    private void showHomePageForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {
        HttpSession session = req.getSession();
        Integer idUser = (Integer) session.getAttribute("idAccount");
        User useForCreateStatus = userDAO.getUserById(idUser);
        req.setAttribute("useForCreateStatus", useForCreateStatus);
        req.getRequestDispatcher("/home").forward(req,resp);
    }

}