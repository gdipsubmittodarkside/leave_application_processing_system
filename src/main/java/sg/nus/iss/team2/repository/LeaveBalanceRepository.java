package sg.nus.iss.team2.repository;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.LeaveBalance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
    
    @Query("Select l from LeaveBalance l where l.employee = :emp")
    LeaveBalance findEmployeeLeaveBalance(@Param("emp") Employee emp);
}
