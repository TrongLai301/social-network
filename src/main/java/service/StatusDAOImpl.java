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
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media, namePermission , idUser from status inner join permissionStatus on status.idPermission = permissionStatus.idPermission");
        List<Status> statusList = new ArrayList<>();
        Status status = new Status();
        while (resultSet.next()) {
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDateTime.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setMedia(resultSet.getString("media"));
            status.setPermission(resultSet.getInt("idPermission"));
            status.setIdUser(resultSet.getInt("idUser"));
            statusList.add(status);
        }
        return statusList;
    }

    @Override
    public List<Status> findStatus(String searchContent) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        Status status = new Status();
                ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media ,namePermission,idUser , username from status inner join permissionStatus on status.idPermission = permissionStatus.idPermission inner join user on status.idUser = user.id where description like '%" + searchContent + "%' or username like '%" + searchContent +"%' ");
                while (resultSet.next()) {
                    status.setId(resultSet.getInt("idStatus"));
                    status.setCreateTime(LocalDateTime.parse(resultSet.getString("createTime")));
                    status.setDescription(resultSet.getString("description"));
                    status.setMedia(resultSet.getString("media"));
                    String permission = resultSet.getString("namePermission");
                    status.setIdUser(resultSet.getInt("idUser"));
                    if (permission.equals("private")) {
                        continue;
                    }
                    list.add(status);
                }
        return list;
    }
}
