package service;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements IUserDAO{
    private String localhost = "localhost:3306";
    private String dbname = "SocialNetwork";
    private String username= "root";
    private String password = "Kamito@123";
    private String url = "jdbc:mysql://" + localhost + "/" + dbname;
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }
    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

}
