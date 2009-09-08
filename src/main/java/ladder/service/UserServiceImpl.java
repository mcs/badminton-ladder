package ladder.service;

import ladder.dao.UserDao;
import ladder.model.Password;
import ladder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public boolean isUsernameFree(String username) {
        return userDao.findByLogin(username) == null;
    }

    @Override
    public User register(String username, String password, String email) {
        if (!isUsernameFree(username)) {
            throw new IllegalArgumentException("User already taken");
        }
        
        User u = new User();
        u.setLogin(username);
        u.setPassword(Password.createNewPassword(password));
        u.setEmail(email);
        return userDao.save(u);
    }
}
