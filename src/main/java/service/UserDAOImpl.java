package service;

import DBcontext.DataConnector;
import model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public List<User> getAllUser()  {
        List<User> listFromDb = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call getALlUser() }");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("idaccount"));
                user.setPermission(rs.getInt("permision"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                listFromDb.add(user);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return  listFromDb;
    }
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }
}
