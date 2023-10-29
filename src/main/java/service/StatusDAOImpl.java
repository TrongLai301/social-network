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
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media, namePermission , idUser from status inner join permissionStatus on status.idPermission = permissionStatus.idPermision");
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
    public List<Status> findStatus(String searchContent, String option) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        Status status = new Status();
        switch (option) {
            case "description":
                ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description ,media ,namePermission,idUser from status inner join permissionStatus on status.idPermission = permissionStatus.idPermision  where description like %'" + searchContent + "'%");
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
                break;
            case "nameUser":
                ResultSet resultStatusByNameUser = statement.executeQuery("select idStatus , createTime , description ,media, namePermission , idUser from status inner join permissionStatus on status.idPermission = permissionStatus.idPermission inner join user on status.idUser = user.id where username %'" + searchContent + "'%");
                while (resultStatusByNameUser.next()) {
                    status.setId(resultStatusByNameUser.getInt("idStatus"));
                    status.setCreateTime(LocalDateTime.parse(resultStatusByNameUser.getString("createTime")));
                    status.setDescription(resultStatusByNameUser.getString("description"));
                    status.setMedia(resultStatusByNameUser.getString("media"));
                   String permission = resultStatusByNameUser.getString("namePermission");
                   status.setIdUser(resultStatusByNameUser.getInt("idUser"));
                    if (permission.equals("private")) {
                        continue;
                    }
                    list.add(status);
                }
                break;
        }
        return list;
    }
}
