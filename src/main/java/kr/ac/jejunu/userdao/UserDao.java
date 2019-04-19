package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.get(sql, params);
    }

    public Long add(User user) throws SQLException {
        String sql = "INSERT INTO userinfo(name, password) VALUES (?, ?);";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jdbcContext.add(sql, params);
    }

    public void update(User user) {
        String sql = "UPDATE userinfo SET name=?, password=? WHERE id=?;";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo WHERE id=?;";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }
}
