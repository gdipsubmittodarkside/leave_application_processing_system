package sg.nus.iss.team2.repository;

import sg.nus.iss.team2.model.Leave;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    @Query("SELECT l FROM Leave l WHERE l.employee.employeeId = :emp_id AND l.status IN ('APPROVED', 'WITHDRAW', 'REJECTED')")
    public List<Leave> findLeaveHistoryByEmloyeeId(Long emp_id);

    @Query("SELECT l FROM Leave l WHERE l.employee.employeeId = :emp_id AND l.status IN ('APPROVED') AND (l.startDate <= :theLeaveStartDate AND l.endDate >= :theLeaveStartDate OR l.startDate <= :theLeaveEndDate AND l.startDate >= :theLeaveStartDate OR l.startDate <= :theLeaveStartDate AND l.endDate >= :theLeaveEndDate) ")
    
    public List<Leave> findApprovedLeaveHistoryByEmloyeeId(Long emp_id, LocalDate theLeaveStartDate, LocalDate theLeaveEndDate);

    @Query("SELECT l FROM Leave l WHERE l.employee.employeeId = :emp_id AND l.status IN ('APPLIED', 'UPDATED')")
    public List<Leave> findAppliedAndUpdatedLeavesByEmloyeeId(Long emp_id);
}
