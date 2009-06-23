package ladder.dao;

import ladder.model.User;
import org.synyx.hades.dao.ExtendedGenericDao;

public interface UserDao extends ExtendedGenericDao<User, Long> {

    User findByLogin(String login);
}
