package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private UserDao userDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
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
        String name = "양소희";
        String password = "sohi33";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        user.setId(id);

        User resultUser = userDao.get(user.getId());

        assertThat(resultUser.getId(), is(user.getId()));
        assertThat(resultUser.getName(), is(user.getName()));
        assertThat(resultUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void testUpdate() throws SQLException {
        // 한놈 넣어보고
        String name = "김말이";
        String password = "tasty";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        user.setId(id);

        // 그놈 정보 수정한 다음에
        String newName = "떡볶이";
        String newPassword = "delicious";
        user.setName(newName);
        user.setPassword(newPassword);

        // update 하고
        userDao.update(user);

        // 그놈 아이디 넣어서 다시 가져오고
        User result = userDao.get(id);

        // 수정정보 확인
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is(newName));
        assertThat(result.getPassword(), is(newPassword));
    }

    @Test
    public void testDelete() throws SQLException {
        // 한놈 넣어보고
        String name = "강아지";
        String password = "puppy";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        user.setId(id);

        // 그놈 삭제하고
        userDao.delete(id);

        // 다시 가져와서
        User result = userDao.get(id);

        // 있는지 확인
        assertThat(result, nullValue());
    }
}
