package controler;

import DBcontext.DataConnector;
import model.Status;
import model.User;
import service.RelationshipDAO;
import service.StatusDAOImpl;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UserServlet", value = "/user")
@MultipartConfig
public class UserServlet extends HttpServlet {
    PasswordValidate passwordValidate;
    UserDAOImpl userDAO;
    StatusDAOImpl statusDAO;
    RelationshipDAO relationshipDAO;
    private static final String IMG_DIR = "/WEB-INF/img"; // Đường dẫn đến thư mục lưu trữ ảnh và video đính kèm

    @Override
    public void init() throws ServletException {
        passwordValidate = new PasswordValidate();
        userDAO = new UserDAOImpl();
        statusDAO = new StatusDAOImpl();
        relationshipDAO = new RelationshipDAO();
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
                case "uploadNewStatus":
                    uploadNewStatus(req, resp);
                    break;
                case "deleteStatus":
                    deleteStatus(req, resp);
                    break;
                case "editStatus":
                    editStatus(req, resp);
                    break;
                default:
                    break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadNewStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            HttpSession session = request.getSession();
            int userID = (int) session.getAttribute("idAccount");
            String description = request.getParameter("description");
            String mediaPart = request.getParameter("media");
            int permission = Integer.parseInt(request.getParameter("option"));
            if (mediaPart != null){
                try (Connection connection = DataConnector.getConnection()) {
                    // Insert bài viết vào bảng status
                    String insertStatusQuery = "INSERT INTO status (createTime, description, media, idUser, idPermission) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStatusStatement = connection.prepareStatement(insertStatusQuery);
                    insertStatusStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                    insertStatusStatement.setString(2, description);
                    insertStatusStatement.setString(3, mediaPart);
                    insertStatusStatement.setInt(4, userID); // Lấy ID người dùng từ session
                    insertStatusStatement.setInt(5, permission); // Lấy ID quyền
                    insertStatusStatement.executeUpdate();
                    connection.close();
                    response.sendRedirect("/home");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }else {
                try (Connection connection = DataConnector.getConnection()) {
                    // Insert bài viết vào bảng status
                    String insertStatusQuery = "INSERT INTO status (createTime, description, idUser, idPermission) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStatusStatement = connection.prepareStatement(insertStatusQuery);
                    insertStatusStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                    insertStatusStatement.setString(2, description);
                    insertStatusStatement.setInt(3, userID); // Lấy ID người dùng từ session
                    insertStatusStatement.setInt(4, permission); // Lấy ID quyền
                    insertStatusStatement.executeUpdate();
                    connection.close();
                    response.sendRedirect("/home");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
                // Lưu trữ tệp đa phương tiện (hình ảnh, video) vào thư mục trên máy chủ
//            String fileName = mediaPart.getSubmittedFileName();
//            InputStream mediaInput = mediaPart.getInputStream();
//            Path mediaPath = Paths.get("src/main/webapp/images", fileName);
//            Files.copy(mediaInput, mediaPath, StandardCopyOption.REPLACE_EXISTING);

            // Lưu thông tin bài viết và đường dẫn tệp đa phương tiện vào cơ sở dữ liệu

            // Chuyển hướng người dùng sau khi đăng bài viết thành công
        }

    private void deleteStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        int idStatus = Integer.parseInt(request.getParameter("idStatus"));
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("idAccount");
        User user = userDAO.getUserById(userID);
        Status status = statusDAO.getStatusById(idStatus);
        if (user.getId() == status.getIdUser()) {
                try (Connection conn = DataConnector.getConnection()) {
                    String query = "DELETE FROM status WHERE idStatus = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1,idStatus);
                    statement.executeUpdate();
                    session.setAttribute("messageDelete","delete complete");
                    response.sendRedirect("/home");
                }
        }else{
            session.setAttribute("messageDeleteFalse","delete false");
            response.sendRedirect("/home");
        }
    }
    private void editStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        int idStatus = Integer.parseInt(request.getParameter("idStatus"));
        String description = request.getParameter("description");
        String media = request.getParameter("media");
        int permission = Integer.parseInt(request.getParameter("option"));
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("idAccount");
        User user = userDAO.getUserById(userID);
        Status status = statusDAO.getStatusById(idStatus);
        if (user.getId() == status.getIdUser()) {
            try (Connection conn = DataConnector.getConnection()) {
                String query = "update status set description = ? , media = ? , idPermission = ?  WHERE idStatus = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(4,idStatus);
                statement.setString(1,description);
                statement.setString(2,media);
                statement.setInt(3,permission);
                statement.executeUpdate();
                session.setAttribute("messageEditComplete","Edit complete");
                response.sendRedirect("/home");
            }
        }else{
            session.setAttribute("messageEditFalse","Edit false");
            response.sendRedirect("/home");
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
            User currentUser = userDAO.getUserById(idUser);
            List<User> userList = new ArrayList<>();
            User userPost;
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userDAO.getUserById(id);
            req.setAttribute("userFind",user);
            List<Status> defaultPost = statusDAO.getAllStatus();
            List<Status> newPost = new ArrayList<>();
            for (Status status : defaultPost){
                userPost = userDAO.getUserById(status.getIdUser());
                if (status.getIdUser() == id) {
                    if (id == currentUser.getId()) {
                        newPost.add(status);
                        userList.add(userPost);
                    }else{
                        if (status.getPermission() == 2){
                            continue;
                        }
                        newPost.add(status);
                        userList.add(userPost);
                    }
                }
            }
            System.out.println(getRelationship(idUser,id));

            req.setAttribute("relationship",getRelationship(idUser,id));
            req.setAttribute("user",currentUser);
            req.setAttribute("listStatus",newPost);
            req.setAttribute("listUser",userList);
            req.getRequestDispatcher("/user/userProfile/displayProfile/profile.jsp").forward(req, resp);
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
    public String getRelationship(int firstId, int secondID){
        String relationship = relationshipDAO.getRelationshipBetween(firstId,secondID);
        if (firstId == secondID){
            return  "myself";
        }
        if (relationship == null){
            return  "stranger";
        }
        if (relationshipDAO.isSender(firstId)){
            return  "pending";
        }
        if (relationshipDAO.isReceiver(firstId)){
            return  "not_received";
        }
        return relationship;
    }

}