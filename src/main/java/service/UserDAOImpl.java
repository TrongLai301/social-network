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
                user.setId(rs.getInt("id"));
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
     CallableStatement callableStatement = connection.prepareCall("select user.id,username,password,namePermission,status from user inner join permission on user.idPermission = permission.idPermission left join userStatus on user.id = userStatus.idAccount where user.id = '" + id +"'");
     ResultSet resultSet = callableStatement.executeQuery();
     User user = new User();
     while (resultSet.next()){
         user.setId(resultSet.getInt("id"));
         user.setUsername(resultSet.getString("username"));
         user.setPassword(resultSet.getString("password"));
         user.setPermission(resultSet.getString("namePermission"));
         user.setStatus(resultSet.getString("status"));
     }
     connection.close();
     return user;
    }

    @Override
    public void blockUser(int id) throws SQLException , ClassNotFoundException{
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("insert into userStatus(idAccount,status) values (?,?)");
        callableStatement.setInt(1,id);
        callableStatement.setString(2,"block");
        callableStatement.executeUpdate();
        connection.close();
    }
    @Override
    public void unBlockUser(int id) throws SQLException , ClassNotFoundException{
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("delete from userStatus where idAccount = ?");
        callableStatement.setInt(1,id);
        callableStatement.executeUpdate();

        connection.close();
    }
    @Override
    public void updateUser(User user) {

    }
    @Override
    public void editPasswordUser(int id, String newPassword) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("UPDATE user SET password = ? WHERE id = ?");
        callableStatement.setString(1, newPassword);
        callableStatement.setInt(2, id);
        callableStatement.executeUpdate();
    }
    @Override
    public void insertUser(User user){
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call insertUser(?,?,?)}");
            callableStatement.setString(1,user.getUsername());
            callableStatement.setString(2,user.getPassword());
            callableStatement.setString(3,"2");
            callableStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
