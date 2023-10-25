package service;

import model.Status;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IStatusDAO {
    List<Status> getAllStatus() throws SQLException, ClassNotFoundException;
    Status findStatus(Status status) throws SQLException, ClassNotFoundException;

}
