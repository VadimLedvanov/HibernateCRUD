package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/users_db";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
