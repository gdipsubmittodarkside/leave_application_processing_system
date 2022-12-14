package sg.nus.iss.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.nus.iss.team2.model.CompensationRequest;

public interface CompensationRequestRepository extends JpaRepository<CompensationRequest,Long> {
    
    @Query("SELECT c FROM CompensationRequest c WHERE c.employee.employeeId = :emp_id AND c.status IN ('APPLIED', 'UPDATED')")
    public List<CompensationRequest> findRequestByEmpId(Long emp_id);
}
