package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        // 넣을 사용자를 만들고
        String name = "양소희";
        String password = "3333";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        // 넣고 (add)
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        Long id = userDao.add(user);
        user.setId(id);

        // 넣은 애를 가져와보고 (get)
        User result = userDao.get(id);

        // get한 애가 add한 애가 맞는지 확인
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }
}
