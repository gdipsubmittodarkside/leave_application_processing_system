package sg.nus.iss.team2.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.nus.iss.team2.model.User;


public interface UserService {
    List<User> findAllUsers();
    User findUser(Long uid);
    User createUser(User user);
    void removeUser(User user);
    User updateUser(User user);

    User login(String username, String password);
<<<<<<< HEAD
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
=======
    Boolean existsUserByUsername(String username);

>>>>>>> 58e73a3 (all)
}
