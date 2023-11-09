package service;

import DBcontext.DataConnector;
import model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements ICommentDAO{
    @Override
    public List<Comment> getAllCommentByIdStatus(int idStatus) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Comment where idStatus = '" + idStatus +"'");
        List<Comment> commentList = new ArrayList<>();
        while (resultSet.next()){
            Comment comment = new Comment();
            comment.setIdComment(resultSet.getInt("idCmt"));
            comment.setIdUser(resultSet.getInt("idUser"));
            comment.setIdStatus(resultSet.getInt("idStatus"));
            comment.setContent(resultSet.getString("content"));
            comment.setLikeCount(resultSet.getInt("likeCount"));
            commentList.add(comment);
        }
        return commentList;
    }
    @Override
    public int countCommentForStatus(int idStatus) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) AS commentCount FROM Comment WHERE idStatus = '" + idStatus +"'");
        int countComment = 0;
        while (resultSet.next()){
            countComment = resultSet.getInt("commentCount");
        }
        return countComment;
    }
}
