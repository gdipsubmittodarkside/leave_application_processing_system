package sg.nus.iss.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.team2.model.Employee;

public interface EmployeeRespository extends JpaRepository<Employee,Long>{
    
}
