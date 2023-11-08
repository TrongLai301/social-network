package service;

import model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentDAO {
    List<Comment> getAllCommentByIdStatus(int idStatus) throws SQLException, ClassNotFoundException;
    int countCommentForStatus(int idStatus) throws SQLException, ClassNotFoundException;
}
