package sg.nus.iss.team2.repository;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    @Query("Select l from Leave l where l.employee = :emp")
    List<Leave> findEmployeeLeave(@Param("emp") Employee emp);

}
