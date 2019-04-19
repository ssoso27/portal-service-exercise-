package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long add(User user) throws SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(name, password) VALUES (?, ?);");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                return preparedStatement;
            }
        };
        return jdbcContext.jdbcContextForAdd(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userinfo SET name=?, password=? WHERE id=?; ");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setLong(3, user.getId());
                return preparedStatement;
            }
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo WHERE id=?;");
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
