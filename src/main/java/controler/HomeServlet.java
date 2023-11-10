package controler;

import model.Comment;
import model.Like;
import model.Status;
import model.User;
import service.CommentDAOImpl;
import service.RelationshipDAO;
import service.StatusDAOImpl;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    StatusDAOImpl statusDAO;
    UserDAOImpl userDAO;
    CommentDAOImpl commentDAO;
    RelationshipDAO relationshipDAO;
    UserServlet userServlet = new UserServlet();

    @Override
    public void init() throws ServletException {
        statusDAO = new StatusDAOImpl();
        userDAO = new UserDAOImpl();
        commentDAO = new CommentDAOImpl();
        relationshipDAO = new RelationshipDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "search":
                findStatusByName(req, resp);
                break;
            case "addComment":
                addCommentToDatabase(req, resp);
            default:
                break;
        }
    }

    private void addCommentToDatabase(HttpServletRequest req, HttpServletResponse resp) {
        try {
        String cmt = req.getParameter("commentContent");
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        Integer idUser = (Integer) req.getSession().getAttribute("idAccount");
        User userNow = userDAO.getUserById(idUser);
        Comment commentToAdd = new Comment();
        commentToAdd.setIdUser(idUser);
        commentToAdd.setIdStatus(idStatus);
        commentToAdd.setContent(cmt);
        //code add vao csdl
        statusDAO.addComment(commentToAdd);

            resp.sendRedirect("/home?idStatusCmt=" + idStatus);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void findStatusByName(HttpServletRequest request, HttpServletResponse response) {
        String searchContent = request.getParameter("searchContent");
        List<User> userList = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User user = userDAO.getUserById(idUser);
            List<Status> list = statusDAO.findStatus(searchContent);
            List<Status> post = new ArrayList<>();
            List<User> userResult = statusDAO.getAllUserToSearch(searchContent);
            List<Like> listLike = new ArrayList<>();
            User userPost;
            for (Status status : list) {
                userPost = userDAO.getUserById(status.getIdUser());
                if (status.getIdUser() != idUser) {
                    if (status.getPermission() == 2) {
                        continue;
                    }
                    int countComment = commentDAO.countCommentForStatus(status.getId());
                    session.setAttribute("countComment", countComment);
                    post.add(status);
                    userList.add(userPost);
                } else {
                    int countComment = commentDAO.countCommentForStatus(status.getId());
                    session.setAttribute("countComment", countComment);
                    post.add(status);
                    userList.add(userPost);
                }
                if (userDAO.checkLikedPost(status.getId(), idUser)) {
                    Like like = new Like();
                    like.setStatus(1);
                    listLike.add(like);
                } else {
                    Like like = new Like();
                    like = null;
                    listLike.add(like);
                }
            }
            request.setAttribute("check", listLike);
            request.setAttribute("UserResult", userResult);
            request.setAttribute("user", user);
            request.setAttribute("listStatusFindBySearch", post);
            request.setAttribute("listUser", userList);
            request.getRequestDispatcher("display-home/resultSearchFB.jsp").forward(request, response);
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
                showHomePage(req, resp);
                break;
        }
    }


    public void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> userList = new ArrayList<>();
            HttpSession session = request.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            User user = userDAO.getUserById(idUser);
            User userPost;
            List<Status> list = statusDAO.getAllStatus();
            List<Status> post = new ArrayList<>();
            List<Like> listLike = new ArrayList<>();
            String idStatus = request.getParameter("idStatusCmt");
            List<Comment> comments = new ArrayList<>();
            List<User> userComment = new ArrayList<>();

            if (idStatus != null && !idStatus.isEmpty()) {
                comments = statusDAO.getAllCommentByIdStatus(Integer.parseInt(idStatus));
                userComment = userDAO.getAllUserByIdStatus(Integer.parseInt(idStatus));
                for (User c : userComment
                ) {
                    System.out.println(c.getId());
                }
                for (Status status : list) {

                    userPost = userDAO.getUserById(status.getIdUser());
                    if (status.getIdUser() != idUser) {
                        if (status.getPermission() == 2) {
                            continue;
                        }
                    }
                    if (userDAO.checkLikedPost(status.getId(), idUser)) {
                        Like like = new Like();
                        like.setStatus(1);
                        listLike.add(like);
                    } else {
                        Like like = new Like();
                        like = null;
                        listLike.add(like);
                    }

                    status.setCommentCount(commentDAO.countCommentForStatus(status.getId()));
                    post.add(status);
                    userList.add(userPost);
                }
                request.setAttribute("userComment", userComment);
                request.setAttribute("comments", comments);
                request.setAttribute("check", listLike);
                request.setAttribute("user", user);
                request.setAttribute("listStatus", post);
                request.setAttribute("listUser", userList);
            }
                request.getRequestDispatcher("display-home/homeFB.jsp").forward(request, response);
            } catch(SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
                /*--------------Comment-----------------*/

    }
}

