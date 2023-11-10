package service;

import DBcontext.DataConnector;
import model.Comment;
import model.Status;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatusDAOImpl implements IStatusDAO {
    @Override
    public List<Status> getAllStatus() throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media, status.idPermission , idUser , likeCount from status");
        List<Status> statusList = new ArrayList<>();
        while (resultSet.next()) {
            Status status = new Status();
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setMedia(resultSet.getString("media"));
            status.setPermission(resultSet.getInt("idPermission"));
            status.setIdUser(resultSet.getInt("idUser"));
            status.setLikeCount(resultSet.getInt("likeCount"));
            statusList.add(status);
        }
        return statusList;
    }

    @Override
    public List<User> getAllUserToSearch(String searchContent) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<User> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(" select * from user where username  like '%" + searchContent + "%' or fullname like '%" + searchContent + "%'");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setName(resultSet.getString("fullname"));
            user.setAddress(resultSet.getString("address"));
            user.setHobby(resultSet.getString("hobby"));
            list.add(user);
        }
        return list;
    }

    @Override
    public Status getStatusById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        Status status = null;
        ResultSet resultSet = statement.executeQuery("select * from status where idStatus = '" + id + "'");
        while (resultSet.next()) {
            status = new Status();
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setMedia(resultSet.getString("media"));
            status.setPermission(resultSet.getInt("idPermission"));
            status.setIdUser(resultSet.getInt("idUser"));
            status.setLikeCount(resultSet.getInt("likeCount"));
            list.add(status);
        }
        return status;
    }

    @Override
    public List<Status> findStatus(String searchContent) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media ,status.idPermission ,idUser , username , namePermission ,likeCount from status inner join permissionStatus on status.idPermission = permissionStatus.idPermission left join user on status.idUser = user.id where description like '%" + searchContent + "%' or fullname like '%" + searchContent + "%' ");
        while (resultSet.next()) {
            Status status = new Status();
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setMedia(resultSet.getString("media"));
            status.setPermission(resultSet.getInt("idPermission"));
            status.setIdUser(resultSet.getInt("idUser"));
            status.setLikeCount(resultSet.getInt("likeCount"));
            list.add(status);
        }
        return list;
    }

    @Override
    public List<Comment> getAllCommentByIdStatus(int idStatus) {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("select * from comment where idStatus = ?");
            cs.setInt(1,idStatus);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt("idCmt"));
                comment.setIdUser(rs.getInt("idUser"));
                comment.setIdStatus(rs.getInt("idStatus"));
                comment.setContent(rs.getString("content"));
                comment.setLikeCount(rs.getInt("likeCount"));
                comment.setCreatedTime(rs.getTime("createTime"));
                comments.add(comment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public void addComment(Comment comment) {
        try {
            Connection connection = DataConnector.getConnection();
            CallableStatement cs = connection.prepareCall("insert into comment (idUser, idStatus, content) values (?,?,?)");
            cs.setInt(1,comment.getIdUser());
            cs.setInt(2,comment.getIdStatus());
            cs.setString(3,comment.getContent());
            cs.executeUpdate();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
