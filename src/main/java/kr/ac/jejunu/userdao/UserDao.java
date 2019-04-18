package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    private User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {

        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long add(User user) throws SQLException {
        StatementStrategy statementStrategy = new AddStatementStrategy(user);
        return jdbcContext.jdbcContextForAdd(statementStrategy);
    }

    private Long jdbcContextForAdd(StatementStrategy statementStrategy) throws SQLException {
        return jdbcContext.jdbcContextForAdd(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    private void jdbcContextForUpdate(StatementStrategy statementStrategy) {

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    private Long getLastInsertId(Connection connection) throws SQLException {

        return jdbcContext.getLastInsertId(connection);
    }
}