package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
   List<User> getAllUser() throws SQLException, ClassNotFoundException;
   User getUserById(int id);
   void updateUser(User user);

   void insertUser(User user);
}
