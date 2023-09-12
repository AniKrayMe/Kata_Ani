package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS USER";
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "DROP TABLE IF EXISTS USER";
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM USER WHERE ID = " + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USER";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement prepareStatement = null;
        String sql = "DELETE FROM USER";
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
