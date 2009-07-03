package ladder.service;

import ladder.model.User;

public interface UserService {

    User login(String username, String password);

    boolean isUsernameFree(String username);

    User register(String username, String password, String email);
}
