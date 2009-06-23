package ladder.dao;

import ladder.model.Player;
import org.synyx.hades.dao.ExtendedGenericDao;

public interface PlayerDao extends ExtendedGenericDao<Player, Long> {

    Player findByName(String name);
}
