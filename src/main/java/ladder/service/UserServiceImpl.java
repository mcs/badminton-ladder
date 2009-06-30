package ladder.service;

import ladder.dao.UserDao;
import ladder.model.Password;
import ladder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User login(String username, String password) {
        User user = userDao.findByLogin(username);
        if (user != null) {
            Password p = Password.createPassword(password, user.getPassword().getSalt());
            if (p.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
