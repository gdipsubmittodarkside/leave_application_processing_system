package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.CompensationRequest;

public interface CompensationRequestService {

    List<CompensationRequest> findAllCompensationRequest();

    CompensationRequest findCompensationRequest(Integer compensationRequestId);

    CompensationRequest createCompensationRequest(CompensationRequest compensationRequest);

    CompensationRequest updCompensationRequest(CompensationRequest compensationRequest);

    void removeCompensationRequest(CompensationRequest compensationRequest);

}
