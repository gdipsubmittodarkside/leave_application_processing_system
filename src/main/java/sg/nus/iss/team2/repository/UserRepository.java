package sg.nus.iss.team2.repository;

import sg.nus.iss.team2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUsernameAndPassword(String username, String password);

    Boolean existsUserByUsername(String username);
}
