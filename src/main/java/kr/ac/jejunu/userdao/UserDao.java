package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        };

        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long add(User user) throws SQLException {
        String sql = "INSERT INTO userinfo(name, password) VALUES (?, ?);";
        Object[] params = new Object[]{user.getName(), user.getPassword()};

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        };

        return jdbcContext.jdbcContextForAdd(statementStrategy);
    }

    public void update(User user) {
        String sql = "UPDATE userinfo SET name = ?, password = ? where id = ?;";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }

            return preparedStatement;
        };

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo WHERE id=?;";
        Object[] params = new Object[]{id};

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}