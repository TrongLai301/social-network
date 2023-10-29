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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
            case "search":
                  findStatusByName(req,resp);
                break;
            default:
                break;
        }
    }
    public void findStatusByName(HttpServletRequest request ,HttpServletResponse response){
        String searchContent = request.getParameter("searchContent");
        List<User> userList = new ArrayList<>();
        try {
            User user ;
            HttpSession session = request.getSession();
            Integer idUser = (Integer) session.getAttribute("idAccount");
            List<Status> list = statusDAO.findStatus(searchContent);
            List<Status> post = new ArrayList<>();
            for (Status status : list){
                user = userDAO.getUserById(status.getId());
                if (status.getIdUser() != idUser){
                    if (status.getPermission() == 2){
                        continue;
                    }
                    post.add(status);
                    userList.add(user);
                }
                post.add(status);
                userList.add(user);
            }
            request.setAttribute("listStatusFindBySearch",post);
            request.setAttribute("listUser",userList);
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
                case "Alam" :
                    break;
                default:
                    showHomePage(req,resp);
                    break;
            }
        }
        public void showHomePage(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
            try {
                List<User> userList = new ArrayList<>();
                HttpSession session = request.getSession();
                Integer idUser = (Integer) session.getAttribute("idAccount");
                User user = userDAO.getUserById(idUser);
                User userPost;
                List<Status> list = statusDAO.getAllStatus();
                List<Status> post = new ArrayList<>();
                for (Status status : list){
                    userPost = userDAO.getUserById(status.getId());
                    if (status.getIdUser() != idUser){
                        if (status.getPermission() == 2){
                            continue;
                        }
                        post.add(status);
                        userList.add(userPost);
                    }
                    post.add(status);
                    userList.add(userPost);
                }
                request.setAttribute("user",user);
                request.setAttribute("listStatus",post);
                request.setAttribute("listUser",userList);
                request.getRequestDispatcher("display-home/homeFB.jsp").forward(request,response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

