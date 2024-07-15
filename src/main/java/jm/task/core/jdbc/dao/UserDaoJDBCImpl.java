package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() throws SQLException {
        String sqlCreateTable = "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        try (Statement statement = connection.createStatement()){
            statement.executeLargeUpdate(sqlCreateTable);
        }
    }

    public void dropUsersTable() throws SQLException {
        String sqlDropTable = "DROP TABLE IF EXISTS `users`";
        try (Statement dropStatement = connection.createStatement()) {
            dropStatement.execute(sqlDropTable);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String saveUserQuery = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(saveUserQuery)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String saveUserQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(saveUserQuery)) {
            statement.setLong(1, id);
            statement.execute();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String getAllUsersQuery = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllUsersQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String cleanAllUsersQuery = "DELETE FROM users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(cleanAllUsersQuery);
        }
        }
    }
