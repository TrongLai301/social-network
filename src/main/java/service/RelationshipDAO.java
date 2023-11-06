package service;

import DBcontext.DataConnector;

import java.sql.*;

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
    // Kiểm tra xem có phải là người gửi hay không nếu có trả về true
    @Override
    public boolean isSender(int senderId, int receiverId) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("SELECT receiverId\n" +
                    "from Friendships\n" +
                    "WHERE senderId = ?\n" +
                    "  and status = 'pending' and receiverId = ?");
            cs.setInt(1,senderId);
            cs.setInt(2,receiverId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    // Kiểm tra xem fromID có phải là người được nhận lời mời kê bạn hay không
    @Override
    public boolean isReceiver(int fromId, int toId) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("SELECT senderId\n" +
                    "from Friendships\n" +
                    "WHERE receiverId = ?\n" +
                    "  and status = 'pending' and senderId = ?;");
            cs.setInt(1,fromId);
            cs.setInt(2,toId);
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
    public void addFriend(int senderId, int receiverId) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("update Friendships set status = 'accepted' where (senderId = ? and receiverId = ?) or (senderId = ? and receiverId = ?)");
            cs.setInt(1,senderId);
            cs.setInt(2,receiverId);
            cs.setInt(3,receiverId);
            cs.setInt(4,senderId);
            cs.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRelationshipOf(int firstUser, int secondUser) {
        try {
            Connection con = DataConnector.getConnection();
            CallableStatement cs = con.prepareCall("delete from Friendships where (senderId = ? and receiverId = ?) or (senderId = ? and receiverId = ?)");
            cs.setInt(1,firstUser);
            cs.setInt(2,secondUser);
            cs.setInt(3,secondUser);
            cs.setInt(4,firstUser);
            cs.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int CountFriend(int id) throws SQLException, ClassNotFoundException {
        Statement statement = DataConnector.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("  SELECT COUNT(*) AS friendCount FROM friendships WHERE (senderId  = '" + id +"' or receiverId = '" + id +"') AND status LIKE '%accepted%';");
        int count = 0;
        while (resultSet.next()){
            count = resultSet.getInt("friendCount");
        }
        return count;
    }
}
