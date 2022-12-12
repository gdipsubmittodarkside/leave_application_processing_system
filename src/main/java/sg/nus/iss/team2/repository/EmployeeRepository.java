package sg.nus.iss.team2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.team2.model.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmployeeId(Long id);

    @Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.employeeId = e2.managerId AND e1.employeeId = :eid")
    List<Employee> findSubordinates(@Param("eid") Long eid);


}
