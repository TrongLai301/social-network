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
     ResultSet resultSet = statement.executeQuery("select userAccount.idAccount,username,password,namePermission,status from userAccount inner join permission on userAccount.permission = permission.idPermission left join userStatus on userAccount.idAccount = userStatus.idAccount where userAccount.idAccount = '" + id +"'");
     User user = new User();
     while (resultSet.next()){
         user.setId(resultSet.getInt("idAccount"));
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
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userStatus(idAccount,status) values (?,?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,"block");
        preparedStatement.executeUpdate();
        connection.close();
    }
    @Override
    public void unBlockUser(int id) throws SQLException , ClassNotFoundException{
        Connection connection = DataConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from userStatus where idAccount = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

        connection.close();
    }
    @Override
    public void updateUser(User user) {

    }
    @Override
    public void editPasswordUser(int id, String newPassword) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE userAccount SET password = ? WHERE idAccount = ?");
        pstm.setString(1, newPassword);
        pstm.setInt(2, id);
        pstm.executeUpdate();

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
