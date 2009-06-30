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
    private final String login = "test_01";
    private final String password = "test_pass";
    private User user;

    public UserServiceTest() {
        user = new User();
        user.setLogin(login);
        user.setPassword(Password.createNewPassword(password));
    }

    @Before
    public void initUserService() {
        instance = new UserServiceImpl();
        instance.setUserDao(userDao);
    }

    @Before
    public void initUserDao() {
        when(userDao.findByLogin(login)).thenReturn(user);
    }

    
    @Test
    public void testLogin() {
        User result = instance.login(login, password);
        assertThat(result, is(user));
    }

    @Test
    public void testLoginWrongUser() {
        User result = instance.login("wrong", password);
        assertThat(result, is(not(user)));
    }

    @Test
    public void testLoginWrongPassword() {
        User result = instance.login(login, "wrong");
        assertThat(result, is(not(user)));
    }
}