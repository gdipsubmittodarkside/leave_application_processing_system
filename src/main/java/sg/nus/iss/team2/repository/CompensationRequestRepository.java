package sg.nus.iss.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.team2.model.CompensationRequest;

public interface CompensationRequestRepository extends JpaRepository<CompensationRequest,Integer> {
    
}
