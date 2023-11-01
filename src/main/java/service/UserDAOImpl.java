package service;

import DBcontext.DataConnector;
import model.Status;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public List<User> getAllUser() {
        List<User> listFromDb = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call showUserWithStatus()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setPermission(rs.getString("namePermission"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getString("status"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                listFromDb.add(user);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return listFromDb;
    }

    // debug by tùng
    @Override
    public User getUserById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("select user.id, user.username, user.password, user.fullname, user.avatar, user.email, user.birth, user.address, user.phone, user.hobby, status, namePermission from user inner join permission on user.idPermission = permission.idPermission  where user.id = '" + id + "'");
        ResultSet resultSet = callableStatement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            Date date = resultSet.getDate("birth");
            if (date != null) {
                user.setBirth(LocalDate.parse(resultSet.getString("birth")));
            }
            user.setAvatar(resultSet.getString("avatar"));
            user.setName(resultSet.getString("fullname"));
            user.setAddress(resultSet.getString("address"));
            user.setHobby(resultSet.getString("hobby"));
            user.setPermission(resultSet.getString("namePermission"));
            user.setStatus(resultSet.getString("status"));
        }
        connection.close();
        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("select user.id, user.username, user.password, user.fullname, user.avatar, user.email, user.birth, user.address, user.phone, user.hobby, status, namePermission from user inner join permission on user.idPermission = permission.idPermission  where user.username = '" + name + "'");
        ResultSet resultSet = callableStatement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            Date date = resultSet.getDate("birth");
            if (date != null) {
                user.setBirth(LocalDate.parse(resultSet.getString("birth")));
            }
            user.setAvatar(resultSet.getString("avatar"));
            user.setName(resultSet.getString("fullname"));
            user.setAddress(resultSet.getString("address"));
            user.setHobby(resultSet.getString("hobby"));
            user.setPermission(resultSet.getString("namePermission"));
            user.setStatus(resultSet.getString("status"));
        }
        connection.close();
        return user;
    }

    @Override
    public User findUserWithEmailOrPhone(String email, String phone) {
        User user = new User();
        try {
            Connection connection = DataConnector.getConnection();
            CallableStatement cs = connection.prepareCall("select * from user where email = ? or phone = ?;");
            cs.setString(1, email);
            cs.setString(2, phone);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void addBlockUser(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("update user set status = 'block' where id = ?");
        callableStatement.setInt(1, id);
        callableStatement.executeUpdate();
        connection.close();
    }

    @Override
    public void removeBlockUser(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        CallableStatement callableStatement = connection.prepareCall("update user set status = 'working' where id = ?");
        callableStatement.setInt(1, id);
        callableStatement.executeUpdate();
        connection.close();
    }

    @Override
    public void updateUser(User user) {
        try {
            Connection connection = DataConnector.getConnection();
            CallableStatement cs = connection.prepareCall("UPDATE user u set u.email = ?, u.phone = ?, u.birth = ?, u.avatar = ?, u.fullname = ?, u.address = ? , u.hobby = ? where id = ?");
            cs.setString(1, user.getEmail());
            cs.setString(2, user.getPhone());
            cs.setString(3, null);
            if (user.getBirth() != null) {
                cs.setString(3, String.valueOf(user.getBirth()));
            }
            cs.setString(4, user.getAvatar());
            cs.setString(5, user.getName());
            cs.setString(6, user.getAddress());
            cs.setString(7, user.getHobby());
            cs.setInt(8, user.getId());
            cs.executeUpdate();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void insertUser(User user) {
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call insertUser(?,?,?,?,?,?)}");
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getEmail());
            Date date = Date.valueOf(user.getBirth());
            callableStatement.setDate(4, date);
            callableStatement.setString(5, user.getPhone());
            callableStatement.setInt(6, 2);
            callableStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertStatus(Status status) {
        try {
            Connection connection = DataConnector.getConnection();
            CallableStatement callableStatement = connection.prepareCall("insert into status (description, idUser, createTime, media, idPermission) values (?,?,?,?,?)");
            callableStatement.setString(1, status.getDescription());
            callableStatement.setInt(2, status.getIdUser());
            callableStatement.setDate(3, Date.valueOf(status.getCreateTime()));
            callableStatement.setString(4, status.getMedia());
            callableStatement.setInt(5, status.getPermission());
            callableStatement.executeUpdate();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
