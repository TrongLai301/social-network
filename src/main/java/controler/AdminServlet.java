package controler;

import model.User;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Class admin servlet dùng để hiển thị và sử lý logic liên quan đến trang admin ví dụ như block or view

@WebServlet(name = "AdminServlet",value = "/admin")
public class AdminServlet extends HttpServlet {
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
            List<User> defaultListUser = userDAO.getAllUser();
            request.setAttribute("defaultListUser", defaultListUser);
            request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
        }else{
            if (user.getStatus()==null){
                userDAO.addBlockUser(id);
                request.setAttribute("messageBlock","thanh cong");
                List<User> defaultListUser = userDAO.getAllUser();
                request.setAttribute("defaultListUser", defaultListUser);
                request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
            }else {
                if (user.getStatus().equals("working")){
                    userDAO.addBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("messageBlock","thanh cong");
                    request.setAttribute("defaultListUser", defaultListUser);
                    request.getRequestDispatcher("/admin/home.jsp").forward(request,response);
                }else {
                    userDAO.removeBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("messageRemove","thanh cong");
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

    public void directToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> defaultListUser = userDAO.getAllUser();
        req.setAttribute("defaultListUser", defaultListUser);
        req.getRequestDispatcher("/admin/home.jsp").forward(req,resp);
    }
}
