package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public Long add(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO userinfo(name, password) VALUES (?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }
}
