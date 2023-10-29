package service;

import model.Status;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IStatusDAO {
    List<Status> getAllStatus() throws SQLException, ClassNotFoundException;
    List<Status> findStatus(String searchContent) throws SQLException, ClassNotFoundException;
}
