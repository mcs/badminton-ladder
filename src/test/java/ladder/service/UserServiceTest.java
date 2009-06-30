package ladder.service;

import ladder.BadmintonTestFixture;
import ladder.dao.UserDao;
import ladder.model.Password;
import ladder.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UserServiceTest extends BadmintonTestFixture {

    @Mock
    private UserDao userDao;
    private UserServiceImpl instance;

    public UserServiceTest() {
    }

    @Before
    public void initUserService() {
        instance = new UserServiceImpl();
        instance.setUserDao(userDao);
    }

    @Test
    public void testLogin() {
        String username = "test_01";
        String password = "test_pass";

        User user = new User();
        user.setLogin(username);
        user.setPassword(Password.createNewPassword(password));

        when(userDao.findByLogin(username)).thenReturn(user);

        User result = instance.login(username, password);
        assertThat(result, is(user));
    }

    @Test
    public void testLoginWrongUser() {
        String username = "test_01";
        String password = "test_pass";

        User user = new User();
        user.setLogin(username);
        user.setPassword(Password.createNewPassword(password));

        when(userDao.findByLogin(username)).thenReturn(user);

        User result = instance.login("wrong", password);
        assertThat(result, is(not(user)));
    }

    @Test
    public void testLoginWrongPassword() {
        String username = "test_01";
        String password = "test_pass";

        User user = new User();
        user.setLogin(username);
        user.setPassword(Password.createNewPassword(password));

        when(userDao.findByLogin(username)).thenReturn(user);

        User result = instance.login(username, "wrong");
        assertThat(result, is(not(user)));
    }
}