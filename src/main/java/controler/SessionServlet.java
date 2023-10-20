package controler;

import DBcontext.DataConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*   Xử lý phiên làm việc như login, logout*/
@WebServlet(name = "SessionServlet", value = "/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String actionGet = req.getParameter("actionGet");
        if (actionGet == null) {
            actionGet = "";
        }
        try {
            switch (actionGet) {
                case "logOutToLoginForm":
                    logOut(req, resp);
                    break;
                default:
                    showLoginForm(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String actionPost = req.getParameter("actionPost");
        if (actionPost == null) {
            actionPost = "";
        }
        try {
            switch (actionPost) {
                case "login":
                    loginToHomePage(req, resp);
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doGet
    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp");
        dispatcher.forward(req, resp);
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Xóa session
        req.getSession().invalidate();

        // Trở về trang đăng nhập
        RequestDispatcher dispatcher = req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp");
        dispatcher.forward(req, resp);
    }

    //doPost
    private void loginToHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ req
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Kiểm tra tài khoản có trong database hay không
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DataConnector.getConnection();
            statement = connection.prepareStatement("SELECT namePermission FROM permission inner join userAccount on permission.idPermission = userAccount.permission WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            // Nếu tài khoản tồn tại thì đăng nhập thành công
            if (resultSet.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("namePermission", resultSet.getString("namePermission"));

                // Xác định vai trò của người dùng
                String permission = session.getAttribute("namePermission").toString();

                // Chuyển hướng người dùng đến trang tương ứng với vai trò
                if (permission.equals("admin")) {
                    resp.sendRedirect("/home");
                } else {
                    resp.sendRedirect("/user");
                }
            } else {
                // Đăng nhập thất bại
                RequestDispatcher dispatcher = req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DataConnector.closeConnection(connection, statement, resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
