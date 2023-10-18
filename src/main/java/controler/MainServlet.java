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

@WebServlet(name = "UserServlet",value = "/main")
public class MainServlet extends HttpServlet {
    private UserDAOImpl userDAO ;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        directToListView(req,resp);
    }
    public void directToListView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<User> defaultListUser = userDAO.getAllUser();
        session.setAttribute("defaultListUser", defaultListUser);
        req.getRequestDispatcher("/viewAdmin/user-list-view.jsp").forward(req,resp);
    }
}
