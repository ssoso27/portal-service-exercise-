package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String passwrord;

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(dataSource());
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcContext());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        try {
            simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(classname));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

        simpleDriverDataSource.setUrl(url);
        simpleDriverDataSource.setUsername(username);
        simpleDriverDataSource.setPassword(passwrord);

        return simpleDriverDataSource;
    }
}
