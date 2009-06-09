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
        Ladder ladder = new Ladder();
        ladderDao.persist(ladder);
        System.out.println("Ladder-Id = " + ladder.getId());
        System.out.println("*** Before loop...");
        for (int i = 0; i < 10; i++) {
            Player p = new Player();
            p.setName(PLAYER_PREFIX + (i + 1));
            p.setLadder(ladder);
            ladder.add(p);
            playerDao.persist(p);
            System.out.printf("*** Player %s persisted! Player is in ladder %s\n", p.getId(), p.getLadder());
        }
        System.out.println("*** After loop!");
        System.out.println("Ladder is " + ladder);
    }
}
