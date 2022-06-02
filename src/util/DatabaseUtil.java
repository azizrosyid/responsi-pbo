package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    private  static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String USER = "root";
    private static final String PASS = "secretpassword";

    private static final Connection connection;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}