import service.DataConnector;
import service.UserDAOImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.getAllUser());
    }
}
