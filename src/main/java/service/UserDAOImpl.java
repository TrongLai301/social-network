package service;

import model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public List<User> getAllUser() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listFromDb;
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("select  u.idAccount, u.username, p.email, p.phone, p.date, p.avatar, p.name, p.address, p.hobby from userAccount u join userProfile p on u.idAccount = p.idUser where u.idAccount = ?");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("idAccount"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setBirthdate(rs.getString("date"));
                user.setAvatar(rs.getString("avatar"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setHobby(rs.getString("hobby"));
            }
            System.out.println(user);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Connection con = DataConnector.getConnection();
        try {
            CallableStatement cs = con.prepareCall("UPDATE userprofile p set p.email = ?,  p.phone = ?, p.date = ? , p.avatar = ? , p.name = ?, p.address = ?, p.hobby = ? where idUser = ? ");
            cs.setString(1,user.getEmail());
            cs.setString(2,user.getPhone());
            cs.setString(3,user.getBirthdate());
            cs.setString(4,user.getAvatar());
            cs.setString(5,user.getName());
            cs.setString(6,user.getAddress());
            cs.setString(7,user.getHobby());
            cs.setInt(8,user.getId());
            cs.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
