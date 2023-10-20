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
import java.util.List;

//Class Main servlet dùng để hiển thị và sử lý logic liên quan đến trang home ví dụ như like bài Post

@WebServlet(name = "HomeServlet",value = "/home")
public class HomeServlet extends HttpServlet {
    private UserDAOImpl userDAO ;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet =  req.getParameter("actionGet");
        if (actionGet == null){
            actionGet = "";
        }
        switch (actionGet) {



            // Đoạn code dưới là để chuyển hươớng tới trang home
            default:
                directToHome(req,resp);
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
        HttpSession session = req.getSession();
        List<User> defaultListUser = userDAO.getAllUser();
        session.setAttribute("defaultListUser", defaultListUser);
        req.getRequestDispatcher("/viewAdmin/user-list-view.jsp").forward(req,resp);
    }
}
