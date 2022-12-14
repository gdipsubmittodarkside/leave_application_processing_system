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

    @Query("SELECT l FROM Leave l WHERE l.employee.employeeId = :emp_id AND l.status IN ('APPROVED', 'WITHDRAW', 'REJECTED')")
    public List<Leave> findLeaveHistoryByEmloyeeId(Long emp_id);

    @Query("SELECT l FROM Leave l WHERE l.employee.employeeId = :emp_id AND l.status IN ('APPLIED', 'UPDATED')")
    public List<Leave> findAppliedAndUpdatedLeavesByEmloyeeId(Long emp_id);

}
