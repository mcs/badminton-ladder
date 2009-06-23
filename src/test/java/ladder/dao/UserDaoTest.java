
package ladder.dao;

import ladder.BadmintonTestFixture;
import ladder.model.Role;
import ladder.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest extends BadmintonTestFixture {

    @Autowired
    private UserDao instance;

    @Test
    public void testFindByLoginNotOkay() {
        System.out.println("findByLogin");
        User result = instance.findByLogin("noname");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testFindByLoginOkay() {
        System.out.println("findByLogin");
        User result = instance.findByLogin("admin");
        assertThat(result, is(notNullValue()));
        assertThat(result.getRole(), equalTo(new Role("Administrator")));
    }
}