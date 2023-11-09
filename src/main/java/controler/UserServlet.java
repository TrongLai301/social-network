package controler;

import DBcontext.DataConnector;
import model.Like;
import model.Status;
import model.User;
import service.CommentDAOImpl;
import service.RelationshipDAO;
import org.json.simple.JSONObject;

import service.StatusDAOImpl;
import service.UserDAOImpl;
import service.Validate.PasswordValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UserServlet", value = "/user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PasswordValidate passwordValidate;
    UserDAOImpl userDAO;
    StatusDAOImpl statusDAO;
    RelationshipDAO relationshipDAO;
    CommentDAOImpl commentDAO;
    private static final String IMG_DIR = "/WEB-INF/img"; // Đường dẫn đến thư mục lưu trữ ảnh và video đính kèm

    @Override
    public void init() throws ServletException {
        passwordValidate = new PasswordValidate();
        userDAO = new UserDAOImpl();
        statusDAO = new StatusDAOImpl();
        relationshipDAO = new RelationshipDAO();
        commentDAO = new CommentDAOImpl();
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
                case "moreInformation":
                    moreInformation(req,resp);
                    break;
                case "showUserProfile":
                    showUserProfile(req, resp);
                    break;
                case "updateUserProfile":
                    EditUserProfile(req,resp);
                    break;
                case "showListFriendsUser":
                    showListFriends(req, resp);
                case "likeStatus":
                    likeStatus(req, resp);
                    break;
                case "getLikeCount":
                    getLikeCount(req, resp);
                    break;
                case "showListMutualFriendsUser":
                    showListMutualFriendsUser(req, resp);
                    break;
                default:
                    showHomePageForUser(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showListMutualFriendsUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        HttpSession session = req.getSession();
        int idAccount = (int) session.getAttribute("idAccount");
        int idFriend = Integer.parseInt(req.getParameter("idFriend"));
        req.setAttribute("idFriend", idFriend);
        req.setAttribute("user", userDAO.getUserById(idAccount));
        req.setAttribute("friend", userDAO.getUserById(idFriend));
        List<User> listFriends = new ArrayList<>();
        List<Integer> listAllIdFriends = new ArrayList<>();
        List<Integer> listAllIdFromFriends = new ArrayList<>();
        List<Integer> listIdFriends = new ArrayList<>();
        List<Integer> listAllIdFriendsAccount = new ArrayList<>();
        List<Integer> listIdFriendsAccount = new ArrayList<>();
        User user1 = new User();
        Connection connection = DataConnector.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships");

        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            int sendId = rs.getInt("senderId");
            int receiveId = rs.getInt("receiverId");
            String statusAccepted = rs.getString("status");

            if (idAccount == sendId && statusAccepted.equals("accepted")){
                listAllIdFriendsAccount.add(receiveId);
            } else if (idAccount == receiveId && statusAccepted.equals("accepted")) {
                listAllIdFriendsAccount.add(sendId);
            }
        }
        for (Integer idFriendsAccount : listAllIdFriendsAccount){
            if (!listIdFriendsAccount.contains(idFriendsAccount)){
                listIdFriendsAccount.add(idFriendsAccount);
            }
        }

        ResultSet rs1 = pstm.executeQuery();
        while (rs1.next()){
            int sendId = rs1.getInt("senderId");
            int receiveId = rs1.getInt("receiverId");
            String statusAccepted = rs1.getString("status");

            if (idFriend == sendId && statusAccepted.equals("accepted") && receiveId != idAccount){
                listAllIdFriends.add(receiveId);
            } else if (idFriend == receiveId && statusAccepted.equals("accepted") && sendId != idAccount) {
                listAllIdFriends.add(sendId);
            }
        }
        for (Integer idFriendAccount : listAllIdFriends){
            if (!listIdFriends.contains(idFriendAccount)){
                listIdFriends.add(idFriendAccount);
                listFriends.add(userDAO.getUserById(idFriendAccount));
            }
        }

        req.setAttribute("numberFriends", listFriends.size()+1);

        List<Integer> listIdFromBoth = new ArrayList<>();
        List<User> listUserFriendsFromBoth = new ArrayList<>();
        for (int i =0; i< listIdFriends.size(); i++){
            for (int j=0; j<listIdFriendsAccount.size();j++){
                if (listIdFriends.get(i) == listIdFriendsAccount.get(j)){
                    listIdFromBoth.add(listIdFriendsAccount.get(j));
                    listUserFriendsFromBoth.add(userDAO.getUserById(listIdFriendsAccount.get(j)));
                }
            }
        }
        req.setAttribute("listFriends", listUserFriendsFromBoth);

        for (int i=0; i< listIdFromBoth.size(); i++){
            List<Integer> listIdFromFriends = new ArrayList<>();
            List<Integer> listAllIdFromFriend = new ArrayList<>();
            List<Integer> listIdFriendsFromBoth = new ArrayList<>();
            ResultSet rs2 = pstm.executeQuery();
            while (rs2.next()){
                int sendId = rs2.getInt("senderId");
                int receiveId = rs2.getInt("receiverId");
                String statusAccepted = rs2.getString("status");

                if (listIdFromBoth.get(i) == sendId && statusAccepted.equals("accepted") && receiveId != idAccount){
                    listAllIdFromFriend.add(receiveId);
                } else if (listIdFromBoth.get(i) == receiveId && statusAccepted.equals("accepted")  && sendId != idAccount){
                    listAllIdFromFriend.add(sendId);
                }
            }
            for (Integer idFriends : listAllIdFromFriend){
                if (!listIdFromFriends.contains(idFriends)){
                    listIdFromFriends.add(idFriends);
                }
            }
            for (int j=0; j<listIdFromFriends.size(); j++){
                for (int k=0;k<listIdFriendsAccount.size(); k++){
                    if (listIdFromFriends.get(j) == listIdFriendsAccount.get(k) || idFriend == listIdFriendsAccount.get(k)){
                        if (!listIdFriendsFromBoth.contains(listIdFriendsAccount.get(k))){
                            listIdFriendsFromBoth.add(listIdFriendsAccount.get(k));
                        }
                    }
                }
            }

            listAllIdFromFriends.add(listIdFriendsFromBoth.size());
            req.setAttribute("numberFriendsBoth", listAllIdFromFriends);
        }
        req.getRequestDispatcher("/user/userProfile/friend/mutualFriends.jsp").forward(req, resp);
    }

    private void showListFriends(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        HttpSession session = req.getSession();
        int idAccount = (int) session.getAttribute("idAccount");
        int idFriend = 0;
        if (req.getParameter("idFriend") != null && !req.getParameter("idFriend").isEmpty()){
            idFriend = Integer.parseInt(req.getParameter("idFriend"));
        }
        int id = Integer.parseInt(req.getParameter("id"));
        if (idFriend == 0){
            req.setAttribute("permission", userDAO.getPermissionFriendsUserById(idAccount));
            List<User> listFriendsAccount = new ArrayList<>();
            List<Integer> listAllIdFriendsAccount = new ArrayList<>();
            List<Integer> listIdFriendsAccount = new ArrayList<>();
            List<Integer> listAllIdFromFriends = new ArrayList<>();
            User user = new User();
            User userProfile = userDAO.getUserById(id);
            Connection connection = DataConnector.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships where senderId = '" + id +"' or receiverId = '" + id +"'");
            ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    int sendId = rs.getInt("senderId");
                    int receiveId = rs.getInt("receiverId");
                    String statusAccepted = rs.getString("status");
                    if (userProfile.getId() == idAccount) {

                        if (idAccount == sendId && statusAccepted.equals("accepted")) {
                            listAllIdFriendsAccount.add(receiveId);
                        } else if (idAccount == receiveId && statusAccepted.equals("accepted")) {
                            listAllIdFriendsAccount.add(sendId);
                        }
                    } else {
                        if (idAccount == sendId && statusAccepted.equals("accepted")) {
                            listAllIdFriendsAccount.add(sendId);
                        } else if (idAccount == receiveId && statusAccepted.equals("accepted")) {
                            listAllIdFriendsAccount.add(receiveId);
                        }
                    }
                }
                for (Integer idFriendsAccount : listAllIdFriendsAccount) {
                    if (!listIdFriendsAccount.contains(idFriendsAccount)) {
                        listIdFriendsAccount.add(idFriendsAccount);
                        listFriendsAccount.add(userDAO.getUserById(idFriendsAccount));
                    }
                }
                for (int i = 0; i < listFriendsAccount.size(); i++) {
                    List<Integer> listIdFromFriendsAccount = new ArrayList<>();
                    List<Integer> listAllIdFromFriendsAccount = new ArrayList<>();
                    ResultSet rs1 = pstm.executeQuery();
                    while (rs1.next()) {
                        int sendId = rs1.getInt("senderId");
                        int receiveId = rs1.getInt("receiverId");
                        String statusAccepted = rs1.getString("status");

                        if (listIdFriendsAccount.get(i) == sendId && statusAccepted.equals("accepted") && idAccount != receiveId) {
                            listAllIdFromFriendsAccount.add(receiveId);
                        } else if (listIdFriendsAccount.get(i) == receiveId && statusAccepted.equals("accepted") && idAccount != sendId) {
                            listAllIdFromFriendsAccount.add(sendId);
                        }
                    }
                    for (Integer idFriendAccount : listAllIdFromFriendsAccount) {
                        if (!listIdFromFriendsAccount.contains(idFriendAccount)) {
                            listIdFromFriendsAccount.add(idFriendAccount);
                        }
                    }
                    listAllIdFromFriends.add(listIdFromFriendsAccount.size());
                    req.setAttribute("numberFriendsBoth", listAllIdFromFriends);
                    req.setAttribute("numberFriends", listFriendsAccount.size());
                    req.setAttribute("listFriends", listFriendsAccount);

                }

        }else if (idFriend != 0){
            req.setAttribute("permission", userDAO.getPermissionFriendsUserById(idFriend));
            List<User> listFriends = new ArrayList<>();
            List<Integer> listAllIdFriends = new ArrayList<>();
            List<Integer> listAllIdFromFriends = new ArrayList<>();
            List<Integer> listIdFriends = new ArrayList<>();
            User user1 = new User();
            Connection connection = DataConnector.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships where senderId = '" + id +"' or receiverId = '" + id +"'");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                int sendId = rs.getInt("senderId");
                int receiveId = rs.getInt("receiverId");
                String statusAccepted = rs.getString("status");

                if (idFriend == sendId && statusAccepted.equals("accepted") && receiveId != idAccount){
                    listAllIdFriends.add(receiveId);
                } else if (idFriend == receiveId && statusAccepted.equals("accepted") && sendId != idAccount) {
                    listAllIdFriends.add(sendId);
                }
            }
            for (Integer idFriendAccount : listAllIdFriends){
                if (!listIdFriends.contains(idFriendAccount)){
                    listIdFriends.add(idFriendAccount);
                    listFriends.add(userDAO.getUserById(idFriendAccount));
                }
            }
            for (int i=0; i< listFriends.size(); i++){
                List<Integer> listIdFromFriends = new ArrayList<>();
                List<Integer> listAllIdFromFriend = new ArrayList<>();
                ResultSet rs1 = pstm.executeQuery();
                while (rs1.next()){
                    int sendId = rs1.getInt("senderId");
                    int receiveId = rs1.getInt("receiverId");
                    String statusAccepted = rs1.getString("status");

                    if (listIdFriends.get(i) == sendId && statusAccepted.equals("accepted") && receiveId != idAccount){
                        listAllIdFromFriend.add(receiveId);
                    } else if (listIdFriends.get(i) == receiveId && statusAccepted.equals("accepted")  && sendId != idAccount){
                        listAllIdFromFriend.add(sendId);
                    }
                }
                for (Integer idFriends : listAllIdFromFriend){
                    if (!listIdFromFriends.contains(idFriends)){
                        listIdFromFriends.add(idFriends);
                    }
                }
                listAllIdFromFriends.add(listIdFromFriends.size());
                req.setAttribute("numberFriendsBoth", listAllIdFromFriends);
                req.setAttribute("numberFriends", listFriends.size());
                req.setAttribute("listFriends", listFriends);
            }
        }
        req.setAttribute("user", userDAO.getUserById(id));
        req.getRequestDispatcher("/user/userProfile/friend/friend.jsp").forward(req, resp);
    }

    private void getLikeCount(HttpServletRequest req, HttpServletResponse resp) {
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        userDAO.getLikeCount(idStatus);
    }

    private void likeStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException,ClassNotFoundException,SQLException {
        HttpSession session = req.getSession();
        int idUser = (Integer) session.getAttribute("idAccount");
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        String action = req.getParameter("action");

        if (action.equals("like")) {
           userDAO.updatePlusLikeCount(idStatus,idUser);
        } else if (action.equals("unlike")) {
            userDAO.updateMinusLikeCount(idStatus, idUser);
        }

        int likeCount = userDAO.getLikeCount(idStatus); // Lấy số lượng like mới
        boolean liked = userDAO.checkLikedPost(idStatus, idUser); // Kiểm tra xem người dùng đã like bài viết hay chưa

        // Tạo đối tượng JSON chứa thông tin cập nhật
        JSONObject responseJson = new JSONObject();
        responseJson.put("likeCount", likeCount);
        responseJson.put("liked", liked);

        // Thiết lập kiểu nội dung là JSON
        resp.setContentType("application/json");
        // Gửi phản hồi về client
        resp.getWriter().write(responseJson.toString());
    }



    private void showEditPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/editPassword/editPassword.jsp");
        dispatcher.forward(req, resp);
    }
    public void moreInformation(HttpServletRequest req , HttpServletResponse resp){
        try {
            HttpSession session = req.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User currentUser = userDAO.getUserById(idUser);
            int id = Integer.parseInt(req.getParameter("id"));
            int count = relationshipDAO.CountFriend(id);
            req.setAttribute("countFriend",count);
            User user = userDAO.getUserById(id);
            req.setAttribute("userFind",user);
            req.setAttribute("relationship",getRelationship(idUser,id));
            req.setAttribute("user",currentUser);
            req.getRequestDispatcher("/user/userProfile/displayProfile/information.jsp").forward(req, resp);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            int permission = Integer.parseInt(request.getParameter("option"));

            Part filePart = request.getPart("file");
            String fileName = extractFileName(filePart);
            String mediaPart = "/fileImage/" + fileName;

            if (fileName.isEmpty() && description.isEmpty()){
                session.setAttribute("errorUpload", "error");
                session.setAttribute("pathImage", mediaPart);
                response.sendRedirect("/home");
            } else if(mediaPart != null){
                filePart.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
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
                if (description.isEmpty()){
                    session.setAttribute("error","error");
                    response.sendRedirect("/home");
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
            }

    }


    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/social-network/src/main/webapp/fileImage");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
            Integer idAccount = (Integer) session.getAttribute("idAccount");
            User userFind = userDAO.getUserById(idAccount);
            req.setAttribute("user",userFind);
            int idFriend = 0;
            if (req.getParameter("idFriend") != null){
                idFriend = Integer.parseInt(req.getParameter("idFriend"));
            }

            if (idFriend == 0){
                int id = Integer.parseInt(req.getParameter("id"));
                User userProfile = userDAO.getUserById(id);
                req.setAttribute("userFind",userProfile);
                List<Status> newPost = new ArrayList<>();
                User userPost;
                List<User> userList = new ArrayList<>();
                List<Status> defaultPost = statusDAO.getAllStatus();
                List<Like> listLike = new ArrayList<>();
                for (Status status : defaultPost) {
                    userPost = userDAO.getUserById(status.getIdUser());
                    if (status.getIdUser() == idAccount) {
                        if (idAccount == userProfile.getId()) {
                            if (userDAO.checkLikedPost(status.getId(), idAccount)) {
                                Like like = new Like();
                                like.setStatus(1);
                                listLike.add(like);
                            } else {
                                Like like = new Like();
                                like = null;
                                listLike.add(like);
                            }
                            status.setCommentCount(commentDAO.countCommentForStatus(status.getId()));
                            newPost.add(status);
                            userList.add(userPost);
                        }
                    } else if (status.getIdUser() == userProfile.getId()){
                        if (status.getPermission() == 2) {
                            continue;
                        }
                        if (userDAO.checkLikedPost(status.getId(), idAccount)) {
                            Like like = new Like();
                            like.setStatus(1);
                            listLike.add(like);
                        } else {
                            Like like = new Like();
                            like = null;
                            listLike.add(like);
                        }
                        status.setCommentCount(commentDAO.countCommentForStatus(status.getId()));
                        newPost.add(status);
                        userList.add(userPost);
                    }
                }
                if (userFind.getId() == userProfile.getId()) {

                    List<Integer> listAllIdFriends = new ArrayList<>();
                    User user1 = new User();
                    Connection connection = DataConnector.getConnection();
                    PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships where senderId = '" + id + "' or receiverId = '" + id + "'");
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        int sendId = rs.getInt("senderId");
                        int receiveId = rs.getInt("receiverId");
                        String statusAccepted = rs.getString("status");

                        if (idAccount == sendId && statusAccepted.equals("accepted")) {
                            user1 = userDAO.getUserById(receiveId);
                            listAllIdFriends.add(user1.getId());
                        } else if (idAccount == receiveId && statusAccepted.equals("accepted")) {
                            user1 = userDAO.getUserById(sendId);
                            listAllIdFriends.add(user1.getId());
                        }
                    }
                    List<Integer> listIdFriends = new ArrayList<>();
                    List<User> listFriends = new ArrayList<>();
                    for (Integer idFriend1 : listAllIdFriends) {
                        if (!listIdFriends.contains(idFriend1)) {
                            listIdFriends.add(idFriend1);
                        }
                    }
                    for (Integer idFriend1 : listIdFriends) {
                        User user2 = userDAO.getUserById(idFriend1);
                        listFriends.add(user2);
                    }
                    req.setAttribute("countFriend", relationshipDAO.CountFriend(id));
                    req.setAttribute("relationship", getRelationship(idAccount, id));
                    req.setAttribute("check", listLike);
                    req.setAttribute("numberFriends", listFriends.size());
                    req.setAttribute("listFriends", listFriends);
                    req.setAttribute("listStatus", newPost);
                    req.setAttribute("listUser", userList);
                }else{
                    List<Integer> listAllIdFriends = new ArrayList<>();
                    User user1 = new User();
                    Connection connection = DataConnector.getConnection();
                    PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships where senderId = '" + id + "' or receiverId = '" + id + "'");
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        int sendId = rs.getInt("senderId");
                        int receiveId = rs.getInt("receiverId");
                        String statusAccepted = rs.getString("status");

                        if (idAccount == sendId && statusAccepted.equals("accepted")) {
                            user1 = userDAO.getUserById(sendId);
                            listAllIdFriends.add(user1.getId());
                        } else if (idAccount == receiveId && statusAccepted.equals("accepted")) {
                            user1 = userDAO.getUserById(receiveId);
                            listAllIdFriends.add(user1.getId());
                        }
                    }
                    List<Integer> listIdFriends = new ArrayList<>();
                    List<User> listFriends = new ArrayList<>();
                    for (Integer idFriend1 : listAllIdFriends) {
                        if (!listIdFriends.contains(idFriend1)) {
                            listIdFriends.add(idFriend1);
                        }
                    }
                    for (Integer idFriend1 : listIdFriends) {
                        User user2 = userDAO.getUserById(idFriend1);
                        listFriends.add(user2);
                    }
                    req.setAttribute("countFriend", relationshipDAO.CountFriend(id));
                    req.setAttribute("relationship", getRelationship(idAccount, id));
                    req.setAttribute("check", listLike);
                    req.setAttribute("numberFriends", listFriends.size());
                    req.setAttribute("listFriends", listFriends);
                    req.setAttribute("listStatus", newPost);
                    req.setAttribute("listUser", userList);
                }
            }else {
                int id = Integer.parseInt(req.getParameter("id"));
                User userProfile = userDAO.getUserById(id);
                List<Status> newPost1 = new ArrayList<>();
                req.setAttribute("userFind",userProfile);
                User userPost1;
                List<User> userList1 = new ArrayList<>();
                List<Status> defaultPost = statusDAO.getAllStatus();
                List<Like> listLike = new ArrayList<>();
                for (Status status : defaultPost){
                    userPost1 = userDAO.getUserById(status.getIdUser());
                    if (status.getIdUser() == userFind.getId()) {
                        if (idFriend == userFind.getId()) {
                            if (userDAO.checkLikedPost(status.getId(), idAccount)) {
                                Like like = new Like();
                                like.setStatus(1);
                                listLike.add(like);
                            } else {
                                Like like = new Like();
                                like = null;
                                listLike.add(like);
                            }
                            status.setCommentCount(commentDAO.countCommentForStatus(status.getId()));
                            newPost1.add(status);
                            userList1.add(userPost1);
                        }
                        }else if (status.getIdUser() == userProfile.getId()){
                            if (status.getPermission() == 2){
                                continue;
                            }
                            if (userDAO.checkLikedPost(status.getId(), idAccount)){
                                Like like = new Like();
                                like.setStatus(1);
                                listLike.add(like);
                            }else {
                                Like like = new Like();
                                like = null;
                                listLike.add(like);
                            }
                            status.setCommentCount(commentDAO.countCommentForStatus(status.getId()));
                            newPost1.add(status);
                            userList1.add(userPost1);
                        }
                    }
                List<Integer> listAllIdFriends = new ArrayList<>();
                User user1 = new User();
                Connection connection = DataConnector.getConnection();
                PreparedStatement pstm = connection.prepareStatement("SELECT * from Friendships where senderId = '" + id +"' or receiverId = '" + id +"'");
                ResultSet rs = pstm.executeQuery();
                while (rs.next()){
                    int sendId = rs.getInt("senderId");
                    int receiveId = rs.getInt("receiverId");
                    String statusAccepted = rs.getString("status");

                    if (idFriend == sendId && statusAccepted.equals("accepted") && receiveId != idAccount){
                        user1 = userDAO.getUserById(sendId);
                        listAllIdFriends.add(user1.getId());
                    } else if (idFriend == receiveId && statusAccepted.equals("accepted") && sendId != idAccount) {
                        user1 = userDAO.getUserById(receiveId);
                        listAllIdFriends.add(user1.getId());
                    }
                }
                List<Integer> listIdFriends = new ArrayList<>();
                List<User> listFriends = new ArrayList<>();
                for (Integer idFriend1 : listAllIdFriends){
                    if (!listIdFriends.contains(idFriend1)){
                        listIdFriends.add(idFriend1);
                    }
                }
                for (Integer idFriend1 : listIdFriends){
                    User user2 = userDAO.getUserById(idFriend1);
                    listFriends.add(user2);
                }
                 req.setAttribute("countFriend",relationshipDAO.CountFriend(id));
                 req.setAttribute("relationship",getRelationship(idAccount,id));
                 req.setAttribute("check",listLike);
                req.setAttribute("numberFriends", listFriends.size());
                req.setAttribute("listFriends", listFriends);
                req.setAttribute("listStatus",newPost1);
                req.setAttribute("listUser",userList1);
            }
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
        String permissionShowFriends = req.getParameter("showFriends");
        System.out.println(permissionShowFriends);
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
        userAfterEdit.setPermissionFriends(permissionShowFriends);

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
        if (relationshipDAO.isSender(firstId, secondID)){
            return  "pending";
        }
        if (relationshipDAO.isReceiver(firstId, secondID)){
            return  "not_received";
        }
        return relationship;
    }

}