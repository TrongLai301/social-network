package service;

import DBcontext.DataConnector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationshipDAO implements iRelationshipDAO{
    @Override
    public String getRelationshipBetween(int firstUserId, int secondUserId) {
        String relationShip = null;
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("SELECT status\n" +
                    "from (SELECT DISTINCT CASE\n" +
                    "                          WHEN senderId = ? THEN receiverId\n" +
                    "                          ELSE senderId\n" +
                    "                          END friend,\n" +
                    "                      status\n" +
                    "      FROM Friendships\n" +
                    "      WHERE senderId = ?\n" +
                    "          OR receiverId = ?\n" +
                    "        ) as f\n" +
                    "where friend = ?;\n");
            cs.setInt(1,firstUserId);
            cs.setInt(2,firstUserId);
            cs.setInt(3,firstUserId);
            cs.setInt(4,secondUserId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                relationShip = rs.getString("status");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return relationShip;
    }

    @Override
    public void addRequestAddFriend(int senderId, int receiveId) {
        try {
            Connection connection = DataConnector.getConnection();
            CallableStatement cs = connection.prepareCall("insert into Friendships (senderId, receiverId)\n" +
                    "values (?, ?)");
            cs.setInt(1,senderId);
            cs.setInt(2,receiveId);
            cs.executeUpdate();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isSender(int senderId) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("SELECT receiverId\n" +
                    "from Friendships\n" +
                    "WHERE senderId = ?\n" +
                    "  and status = 'pending';");
            cs.setInt(1,senderId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean isReceiver(int receiveId) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("SELECT senderId\n" +
                    "from Friendships\n" +
                    "WHERE receiverId = ?\n" +
                    "  and status = 'pending';");
            cs.setInt(1,receiveId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
