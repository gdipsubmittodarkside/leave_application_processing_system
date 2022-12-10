package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUser(Integer uid);
    User createUser(User user);
    void removeUser(User user);
    User updateUser(User user);

}
