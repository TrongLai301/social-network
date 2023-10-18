package service;

import model.User;

import java.util.List;

public interface IUserDAO {
   List<User> getAllUser();
   User getUserById(int id);
   void updateUser(User user);

}
