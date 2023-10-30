package service;

import DBcontext.DataConnector;
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
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media, status.idPermission , idUser from status");
        List<Status> statusList = new ArrayList<>();
        while (resultSet.next()) {
            Status status = new Status();
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setMedia(resultSet.getString("media"));
            status.setPermission(resultSet.getInt("idPermission"));
            status.setIdUser(resultSet.getInt("idUser"));
            statusList.add(status);
        }
        return statusList;
    }
    public List<User> getAllUserToSearch(String searchContent) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<User> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(" select * from user where username  like '%" + searchContent +"%' or fullname like '%"+searchContent+"%'");
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
    public List<Status> findStatus(String searchContent) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media ,status.idPermission ,idUser , username , namePermission from status inner join permissionStatus on status.idPermission = permissionStatus.idPermission left join user on status.idUser = user.id where description like '%" + searchContent + "%' or fullname like '%" + searchContent +"%' ");
                while (resultSet.next()) {
                    Status status = new Status();
                    status.setId(resultSet.getInt("idStatus"));
                    status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
                    status.setDescription(resultSet.getString("description"));
                    status.setMedia(resultSet.getString("media"));
                    status.setPermission(resultSet.getInt("idPermission"));
                    status.setIdUser(resultSet.getInt("idUser"));
                    list.add(status);
                }
        return list;
    }
}
