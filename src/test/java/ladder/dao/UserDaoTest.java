
package ladder.dao;

import ladder.BaseTestFixture;
import ladder.model.Role;
import ladder.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest extends BaseTestFixture {

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldFindNooneForWrongLogin() {
        // when
        User result = userDao.findByLogin("noname");
        
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldFindAdminForCorrectLogin() {
        // when
        User result = userDao.findByLogin("admin");
        
        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getRole(), equalTo(new Role("Administrator")));
    }
}