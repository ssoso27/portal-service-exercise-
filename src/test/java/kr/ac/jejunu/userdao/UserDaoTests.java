package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        // 새로운애 넣고
        String name = "소희";
        String password = "sohee";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        user.setId(id);

        // 넣은애 id 가져다가 다시 get해보고
        User result = userDao.get(id);

        // 확인
        assertThat(result.getId(), is(user.getId()));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }

    @Test
    public void testUpdate() throws SQLException {
        // 새로운애 넣고
        String name = "유부초밥";
        String password = "nyam";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        user.setId(id);

        // 걔 정보 수정하고
        String newName = "연어초밥";
        String newPassword = "sushi";
        user.setName(newName);
        user.setPassword(newPassword);

        // update
        userDao.update(user);

        // 다시 get해서
        User result = userDao.get(id);

        // 수정한 애랑 동일한지 확인
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is(newName));
        assertThat(result.getPassword(), is(newPassword));
    }

    @Test
    public void testDelete() {

    }
}
