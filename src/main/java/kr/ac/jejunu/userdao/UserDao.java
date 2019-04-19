package kr.ac.jejunu.userdao;

import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

public class UserDao {
    private JejuJdbcTemplate jdbcTemplate;

    public UserDao(JejuJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Long add(User user) throws SQLException {
        String sql = "INSERT INTO userinfo(name, password) VALUES (?, ?);";
        Object[] params = new Object[]{user.getName(), user.getPassword()};

        return jdbcTemplate.insert(sql, params);
    }

    public void update(User user) {
        String sql = "UPDATE userinfo SET name=?, password=? WHERE id=?;";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo WHERE id=?;";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
