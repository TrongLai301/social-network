package DBcontext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// Class này là để kết nối với database theo file properties
public class DataConnector {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DataConnector.class.getResourceAsStream("/config.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dbUrl = properties.getProperty("db.url");
        String dbUser = properties.getProperty("db.user");
        String dbPassword = properties.getProperty("db.password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
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
