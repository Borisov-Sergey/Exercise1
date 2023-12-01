package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {

        Util util = null;
        String sql = "CREATE TABLE users (" +
                "  ID INT NOT NULL AUTO_INCREMENT," +
                "  NAME VARCHAR(60) NOT NULL," +
                "  LASTNAME VARCHAR(60) NOT NULL," +
                "  AGE INT(3) NOT NULL," +
                "  PRIMARY KEY (ID))";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void dropUsersTable() {
        Util util = null;
        String sql = "DROP TABLE users";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = null;
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void removeUserById(long id) {
        Util util = null;
        String sql = "DELETE FROM users WHERE ID = ?";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet;
        Util util = null;
        String sql = "SELECT * FROM users";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException ignored) {
        }
        return userList;
    }

    public void cleanUsersTable() {
        Util util = null;
        String sql = "DELETE FROM users";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }
}
