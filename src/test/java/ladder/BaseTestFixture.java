package ladder;

import ladder.dao.ChallengeDao;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.dao.UserDao;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
@Transactional
public class BaseTestFixture extends AbstractJpaTests {

    public static final String PLAYER_PREFIX = "Player #";
    @Autowired
    protected LadderDao ladderDao;
    @Autowired
    protected PlayerDao playerDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected ChallengeDao challengeDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void ladderSetUp() {
        Ladder ladder = ladderDao.readAll().get(0);
        assertThat(ladder.size(), is(6));
        for (int i = 0; i < 10; i++) {
            Player p = new Player();
            p.setName(PLAYER_PREFIX + (i + 1));
            ladder.add(p);
            playerDao.save(p);
        }
    }
}
