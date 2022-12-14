package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;

public interface CompensationRequestService {

    List<CompensationRequest> findAllCompensationRequest();

    List<CompensationRequest> findEmployeeCompensationRequest(Employee employee);

    CompensationRequest findCompensationRequest(Long compensationRequestId);

    CompensationRequest createCompensationRequest(CompensationRequest compensationRequest);

    CompensationRequest updCompensationRequest(CompensationRequest compensationRequest);

    void removeCompensationRequest(CompensationRequest compensationRequest);

}
