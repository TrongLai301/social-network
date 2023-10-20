package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
   List<User> getAllUser() throws SQLException, ClassNotFoundException;
   User getUserById(int id) throws SQLException, ClassNotFoundException;
   void updateUser(User user);
   void blockUser(int id) throws SQLException, ClassNotFoundException;
   void unBlockUser(int id) throws SQLException , ClassNotFoundException;

}
