package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataConnector {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/webapp/WEB-INF/classes/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dbUrl = properties.getProperty("db.url");
        String dbUser = properties.getProperty("db.user");
        String dbPassword = properties.getProperty("db.password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
    }
}
