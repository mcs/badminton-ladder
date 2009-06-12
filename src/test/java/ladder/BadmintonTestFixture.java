package ladder;

import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:spring-context.xml")
public class BadmintonTestFixture extends AbstractTransactionalJUnit4SpringContextTests {

    public static final String PLAYER_PREFIX = "Player #";
    @Autowired
    protected LadderDao ladderDao;
    @Autowired
    protected PlayerDao playerDao;

    @Before
    public void ladderSetUp() {
        Ladder ladder = ladderDao.findAll().get(0);
        for (int i = 0; i < 10; i++) {
            Player p = new Player();
            p.setName(PLAYER_PREFIX + (i + 1));
            p.setLadder(ladder);
            // following line is sadly needed in test environment :-(
            ladder.add(p);
            playerDao.persist(p);
        }
    }
}
