package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private Util() {
    }

    public static Connection getConnection() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/schema_name";
        String dbUsername = "Sur";
        String dbPassword = "kjjLVUdf34F52346*(% ";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
