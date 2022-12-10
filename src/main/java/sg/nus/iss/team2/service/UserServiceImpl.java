package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findUser(Integer uid) {
        return userRepository.findById(uid).orElse(null);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User login(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password);
    }


}
