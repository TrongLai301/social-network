package service;

import model.Status;
import model.User;
import model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    List<User> getAllUser() throws SQLException, ClassNotFoundException;

    User getUserById(int id) throws SQLException, ClassNotFoundException;

    User getUserByName(String name) throws SQLException, ClassNotFoundException;
    Integer getPermissionFriendsUserById(int id) throws SQLException, ClassNotFoundException;

    // Tìm user theo email hoặc sdt để kiểm tra email hoặc phone đã tồn tại trong update profile
    User findUserWithEmailOrPhone(String email, String phone);

    void updateUser(User user);

    void addBlockUser(int id) throws SQLException, ClassNotFoundException;

    void removeBlockUser(int id) throws SQLException, ClassNotFoundException;

    void editPasswordUser(int id, String newPassword) throws SQLException, ClassNotFoundException;

    void insertUser(User user);
    void updatePlusLikeCount(int idStatus, int idUser);
    void updateMinusLikeCount(int idStatus, int idUser);

    void insertStatus(Status status);

    boolean checkLikedPost(int idStatus,int idUser);
    List<User> getAllUserByIdStatus(int idStatus);
    List<Admin> getAllUserLogin();
    void insertQuantityUserLogin(Admin admin) throws SQLException, ClassNotFoundException;

    List<Admin> userAccessAdmin(String call);

    List<User> userAccessLogin(String call);
}
