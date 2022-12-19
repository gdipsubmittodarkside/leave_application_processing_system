package sg.nus.iss.team2.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findUser(Long uid) {
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
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userRepository.findAll(pageable);
    }

}
