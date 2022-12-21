package sg.nus.iss.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;

public interface CompensationRequestRepository extends JpaRepository<CompensationRequest,Long> {

    
    @Query("Select c from CompensationRequest c where c.employee = :emp")
    List<CompensationRequest> findEmployeeCompensationRequest(@Param("emp") Employee emp);
    
    @Query("SELECT c FROM CompensationRequest c WHERE c.employee.employeeId = :emp_id AND c.status IN ('APPLIED', 'UPDATED')")
    public List<CompensationRequest> findRequestByEmpId(Long emp_id);

    @Query("SELECT c FROM CompensationRequest c WHERE c.employee.employeeId = :emp_id AND c.status IN ('APPROVED', 'CANCELLED', 'REJECTED')")
    public List<CompensationRequest> findHistoryByEmpId(Long emp_id);
}
