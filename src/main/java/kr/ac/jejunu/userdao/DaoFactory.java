package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
}
