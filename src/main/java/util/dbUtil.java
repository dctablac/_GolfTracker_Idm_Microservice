package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbUtil {
    private static final String DB_DRIVER_CLASS = "driver.class.name";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";

    private static Connection connection = null;
    private static Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/java/util/database.properties"));
            Class.forName(properties.getProperty(DB_DRIVER_CLASS));

            String url = properties.getProperty(DB_URL);
            String user = properties.getProperty(DB_USERNAME);
            String password = properties.getProperty(DB_PASSWORD);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}