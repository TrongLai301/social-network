package DBcontext;

import java.sql.*;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/SocialNetwork";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
