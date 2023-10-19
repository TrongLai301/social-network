package controler;

import DBcontext.DatabaseUtil;
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login-signup/display-signUp-signIn.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra tài khoản có trong database hay không
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT namePermission FROM permission inner join userAccount on permission.idPermission = userAccount.permission WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            // Nếu tài khoản tồn tại thì đăng nhập thành công
            if (resultSet.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("namePermission", resultSet.getString("namePermission"));

                // Xác định vai trò của người dùng
                String permission = session.getAttribute("namePermission").toString();

                // Chuyển hướng người dùng đến trang tương ứng với vai trò
                if (permission.equals("admin")) {
                    response.sendRedirect("admin/home.jsp");
                } else {
                    response.sendRedirect("user/home.jsp");
                }
            } else {
                // Đăng nhập thất bại
                RequestDispatcher dispatcher = request.getRequestDispatcher("login-signup/display-signUp-signIn.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DatabaseUtil.closeConnection(connection, statement, resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

