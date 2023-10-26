package service;

import model.Status;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
   List<User> getAllUser() throws SQLException, ClassNotFoundException;
   User getUserById(int id) throws SQLException, ClassNotFoundException;
   User getUserByName(String name) throws SQLException, ClassNotFoundException;
   // Tìm user theo email hoặc sdt để kiểm tra email hoặc phone đã tồn tại trong update profile
   User findUserWithEmailOrPhone(String email, String phone);
   void updateUser(User user);
   void addBlockUser(int id) throws SQLException, ClassNotFoundException;
   void removeBlockUser(int id) throws SQLException , ClassNotFoundException;
   void editPasswordUser(int id, String newPassword) throws SQLException, ClassNotFoundException;
   void insertUser(User user);
}
