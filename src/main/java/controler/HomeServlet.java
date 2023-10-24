package controler;

import model.User;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//Class Main servlet dùng để hiển thị và sử lý logic liên quan đến trang home ví dụ như like bài Post

@WebServlet(name = "homeServlet",value = "/home")
public class HomeServlet extends HttpServlet {
    private UserDAOImpl userDAO ;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("action");
        if (actionGet == null) {
            actionGet = "";
        }
        try {
            switch (actionGet) {
                case "block":
                    blockUserById(req, resp);
                    break;
                // Đoạn code dưới là để chuyển hươớng tới trang home
                default:
                    directToHome(req, resp);
                    break;
            }
             }catch (SQLException | ClassNotFoundException e){
                   throw new RuntimeException();
        }
    }
    public void blockUserById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.getUserById(id);
        if (user.getPermission().equals("admin")){
            request.setAttribute("message","khong the xoa doi tuong admin");
            request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
        }else{
            if (user.getStatus()==null){
                userDAO.addBlockUser(id);
                List<User> defaultListUser = userDAO.getAllUser();
                request.setAttribute("defaultListUser", defaultListUser);
                request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
            }else {
                if (user.getStatus().equals("working")){
                    userDAO.addBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("defaultListUser", defaultListUser);
                    request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
                }else {
                    userDAO.removeBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("defaultListUser", defaultListUser);
                    request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
                }
            }
        }
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost =  req.getParameter("actionPost");
        if (actionPost == null){
            actionPost = "";
        }
        switch (actionPost) {
            default:
        }
    }

    private void directToLoginView(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void directToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> defaultListUser = userDAO.getAllUser();
        req.setAttribute("defaultListUser", defaultListUser);
        req.getRequestDispatcher("/admin/home.jsp").forward(req,resp);
    }
}
