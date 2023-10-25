package service;

import DBcontext.DataConnector;
import model.Status;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StatusDAOImpl implements IStatusDAO {
    @Override
    public List<Status> getAllStatus() throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description , img , video , namePermission from status inner join permissionStatus on status.idPermission = permissionStatus.idPermision");
        List<Status> statusList = new ArrayList<>();
        Status status = new Status();
        while (resultSet.next()) {
            status.setId(resultSet.getInt("idStatus"));
            status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
            status.setDescription(resultSet.getString("description"));
            status.setImg(resultSet.getString("img"));
            status.setVideo(resultSet.getString("video"));
            status.setPermission(resultSet.getString("idPermission"));
            statusList.add(status);
        }
        return statusList;
    }

    @Override
    public List<Status> findStatus(Status status, String option, User user) throws SQLException, ClassNotFoundException {
        Connection connection = DataConnector.getConnection();
        Statement statement = connection.createStatement();
        List<Status> list = new ArrayList<>();
        switch (option) {
            case "description":
                ResultSet resultSet = statement.executeQuery("select idStatus , createTime , description , img , video , namePermission from status inner join permissionStatus on status.idPermission = permissionStatus.idPermision where description like %'" + status.getId() + "'%");
                while (resultSet.next()) {
                    status.setId(resultSet.getInt("idStatus"));
                    status.setCreateTime(LocalDate.parse(resultSet.getString("createTime")));
                    status.setDescription(resultSet.getString("description"));
                    status.setImg(resultSet.getString("img"));
                    status.setVideo(resultSet.getString("video"));
                    if (status.getPermission().equals("private")) {
                        continue;
                    }
                    list.add(status);
                }
                break;
            case "nameUser":
                ResultSet resultStatusByNameUser = statement.executeQuery("select idStatus , createTime , description , img , video , namePermission , idUser from status inner join permissionStatus on status.idPermission = permissionStatus.idPermision inner join status.idUser = user.id where idUser like %'" + user.getId() + "'%");
                while (resultStatusByNameUser.next()) {
                    status.setId(resultStatusByNameUser.getInt("idStatus"));
                    status.setCreateTime(LocalDate.parse(resultStatusByNameUser.getString("createTime")));
                    status.setDescription(resultStatusByNameUser.getString("description"));
                    status.setImg(resultStatusByNameUser.getString("img"));
                    status.setVideo(resultStatusByNameUser.getString("video"));
                    if (status.getPermission().equals("private")) {
                        continue;
                    }
                    list.add(status);
                }
                break;
        }
          return list;
    }
}
