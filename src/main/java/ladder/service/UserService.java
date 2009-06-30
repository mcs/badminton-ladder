package ladder.service;

import ladder.model.User;

public interface UserService {

    User login(String username, String password);
}
