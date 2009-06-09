package ladder.dao;

import ladder.model.Player;

public interface PlayerDao extends AbstractDao<Player> {

    Player findByName(String name);
}
