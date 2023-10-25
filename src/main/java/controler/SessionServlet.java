package controler;

import DBcontext.DataConnector;
import model.User;
import org.json.simple.JSONObject;
import service.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*   Xử lý phiên làm việc như login, logout*/
@WebServlet(name = "SessionServlet", value = "/session")
public class SessionServlet extends HttpServlet {
    UserDAOImpl userDAO = new UserDAOImpl();

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
                case "signUp":
                    signupNewUser(req, resp);
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doGet
    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.sendRedirect("login-signup/display-signUp-signIn.jsp");
        try {
            req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }


    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Xóa session
        req.getSession().invalidate();

        // Trở về trang đăng nhập
//        resp.sendRedirect("login-signup/display-signUp-signIn.jsp");
        try {
            req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    //doPost
    private void loginToHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        // Lấy thông tin đăng nhập từ req
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userDAO.getUserByName(username);

            // Kiểm tra tài khoản có trong database hay không
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                connection = DataConnector.getConnection();
                statement = connection.prepareStatement("SELECT id, namePermission FROM permission inner join user on permission.idPermission = user.idPermission WHERE username = ? AND password = ?");
                statement.setString(1, username);
                statement.setString(2, password);
                resultSet = statement.executeQuery();

                // Nếu tài khoản tồn tại thì đăng nhập thành công
                if (resultSet.next()) {
                    HttpSession session = req.getSession();
                    int id = resultSet.getInt("id");
                    session.setAttribute("idAccount", id);
                    session.setAttribute("username", username);
                    session.setAttribute("namePermission", resultSet.getString("namePermission"));
                    // Xác định vai trò của người dùng
                    String permission = session.getAttribute("namePermission").toString();

                    // Chuyển hướng người dùng đến trang tương ứng với vai trò
                    if (permission.equals("admin")) {
                        resp.sendRedirect("/admin");
                    } else {
                        if (user.getStatus().equals("block")){
                            req.setAttribute("message","tài khoản bị chặn");
                            req.getRequestDispatcher("login-signup/display-signUp-signIn.jsp").forward(req,resp);
                        }else{
                            resp.sendRedirect("/user");
                        }
                    }
                } else {
                    // Đăng nhập thất bại
                    req.setAttribute("messageError","tài khoản không đúng hoặc mật khẩu không trùng khớp");
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



    public boolean checkUser(List<User> users, String username) {
        for (User check : users
        ) {
            if (check.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void signupNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lấy ttin đki từ req.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);
        List<User> listUser = userDAO.getAllUser();
        String notify;
        JSONObject jsonObject = new JSONObject();
        if (!checkUser(listUser, username)) {
            notify = "The user is exist";
        } else {
            notify = "success";
            userDAO.insertUser(user);
        }
        jsonObject.put("notifyUser",notify);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonObject);
    }
}
