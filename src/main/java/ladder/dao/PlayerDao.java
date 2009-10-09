package ladder.dao;

import java.util.List;
import ladder.model.Player;
import ladder.model.User;
import org.synyx.hades.dao.ExtendedGenericDao;

public interface PlayerDao extends ExtendedGenericDao<Player, Long> {

    Player findByName(String name);
    List<Player> findByUser(User user);
}
