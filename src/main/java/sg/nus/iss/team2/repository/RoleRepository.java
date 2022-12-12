package sg.nus.iss.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.team2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
