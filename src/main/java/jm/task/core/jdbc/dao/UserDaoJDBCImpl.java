package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static long USERS_COUNT = 0;
    private static final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Person(" +
                            "id int," +
                            "name varchar(45)," +
                            "lastName varchar(45)," +
                            "age int)"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String SQL = "DROP TABLE IF EXISTS Person";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Person VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, (int) ++USERS_COUNT);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);

            preparedStatement.executeUpdate();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Person WHERE id=?"
            );

            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Person"
            );

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();

                user.setId((long) rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));

                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "TRUNCATE TABLE Person"
            );

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
