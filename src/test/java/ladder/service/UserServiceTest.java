package ladder.service;

import ladder.BaseTestFixture;
import ladder.dao.UserDao;
import ladder.model.Password;
import ladder.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UserServiceTest extends BaseTestFixture {

    @Mock
    private UserDao userDao;
    private UserServiceImpl userService;
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
        userService = new UserServiceImpl();
        userService.setUserDao(userDao);
    }

    @Before
    public void initUserDao() {
        given(userDao.findByLogin(login)).willReturn(user);
    }

    
    @Test
    public void testLogin() {
        User result = userService.login(login, password);
        assertThat(result, is(user));
    }

    @Test
    public void testLoginWrongUser() {
        User result = userService.login("wrong", password);
        assertThat(result, is(not(user)));
    }

    @Test
    public void testLoginWrongPassword() {
        User result = userService.login(login, "wrong");
        assertThat(result, is(not(user)));
    }

    @Test
    public void testRegisterOkay() {
        User mockUser = new User();
        mockUser.setLogin("not_used");
        when(userDao.save(mockUser)).thenReturn(mockUser);

        User u = userService.register("not_used", "1234", "1@1.1");
        assertThat(u, is(mockUser));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNotOkay() {
        userService.register(login, password, "1@1.1");
    }

    @Test
    public void testIsUsernameFree() {
        assertThat(userService.isUsernameFree(login), is(false));
        assertThat(userService.isUsernameFree("wrong"), is(true));
    }
}