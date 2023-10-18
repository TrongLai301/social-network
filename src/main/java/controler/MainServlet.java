package controler;

import service.DataConnector;
import service.IUserDAO;
import service.UserDAOImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "UserServlet",value = "/main")
public class MainServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{

        ServletContext context = getServletContext();
        InputStream input = context.getResourceAsStream("/WEB-INF/classes/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");

        try {
            DataConnector.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/viewAdmin/user-list-view.jsp").forward(req,resp);
    }
}
