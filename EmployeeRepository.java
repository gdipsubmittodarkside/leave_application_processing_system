package sg.nus.iss.team2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
