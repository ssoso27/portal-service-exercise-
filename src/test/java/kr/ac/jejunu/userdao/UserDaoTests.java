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
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "양소희";
        String password = "sohi33";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        Long id = userDao.add(user);
        user.setId(id);

        User resultUser = userDao.get(user.getId());

        assertThat(resultUser.getId(), is(user.getId()));
        assertThat(resultUser.getName(), is(user.getName()));
        assertThat(resultUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "헐크";
        String password = "1111";
        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
