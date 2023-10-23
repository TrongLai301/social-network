package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
   List<User> getAllUser() throws SQLException, ClassNotFoundException;
   User getUserById(int id) throws SQLException, ClassNotFoundException;
   void updateUser(User user);
   void addBlockUser(int id) throws SQLException, ClassNotFoundException;
   void removeBlockUser(int id) throws SQLException , ClassNotFoundException;
   void editPasswordUser(int id, String newPassword) throws SQLException, ClassNotFoundException;
   void insertUser(User user);
}
