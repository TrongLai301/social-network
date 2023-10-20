package service;

import DBcontext.DataConnector;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public List<User> getAllUser()  {
        List<User> listFromDb = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call showUserWithStatus() }");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("idAccount"));
                user.setPermission(rs.getString("namePermission"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getString("status"));
                listFromDb.add(user);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return  listFromDb;
    }
    @Override
    public User getUserById(int id) throws SQLException, ClassNotFoundException {
     Connection connection = DataConnector.getConnection();
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("select idAccount,username,password,namePermission from userAccount inner join permission on userAccount.permission = permission.idPermission where idAccount = '" + id +"'");
        User user = new User();
     while (resultSet.next()){
         user.setId(resultSet.getInt("idAccount"));
         user.setUsername(resultSet.getString("username"));
         user.setPassword(resultSet.getString("password"));
         user.setPermission(resultSet.getString("namePermission"));
     }
     connection.close();
     return user;
    }

    @Override
    public void blockUser(int id) throws SQLException , ClassNotFoundException{
        Connection connection = DataConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userStatus(idAccount,status) values (?,?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,"block");
        preparedStatement.executeUpdate();
        connection.close();
    }
    @Override
    public void unBlockUser(int id) throws SQLException , ClassNotFoundException{
        Connection connection = DataConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update userStatus set status = ? where id = ?");
        preparedStatement.setInt(2,id);
        preparedStatement.setString(1,"");
        preparedStatement.executeUpdate();
        connection.close();
    }
    @Override
    public void updateUser(User user) {

    }
}
