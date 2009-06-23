package ladder;

import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
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
public class BadmintonTestFixture extends AbstractJpaTests {

    public static final String PLAYER_PREFIX = "Player #";
    @Autowired
    protected LadderDao ladderDao;
    @Autowired
    protected PlayerDao playerDao;

    @Before
    public void ladderSetUp() {
        MockitoAnnotations.initMocks(this);

        Ladder ladder = ladderDao.readAll().get(0);
        for (int i = 0; i < 10; i++) {
            Player p = new Player();
            p.setName(PLAYER_PREFIX + (i + 1));
            p.setLadder(ladder);
            // following line is sadly needed in test environment :-(
            ladder.add(p);
            playerDao.save(p);
        }
    }
}
