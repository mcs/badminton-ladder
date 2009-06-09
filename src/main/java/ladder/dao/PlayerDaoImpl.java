package ladder.dao;

import ladder.model.Player;

public class PlayerDaoImpl extends AbstractDaoImpl<Player> implements PlayerDao {

    @Override
    public Player findByName(String name) {
        return (Player) em
                .createQuery("SELECT p FROM Player p WHERE p.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
