package sg.nus.iss.team2.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.repository.CompensationRequestRepository;

@Service
public class CompensationRequestServiceImpl implements CompensationRequestService {

    @Resource
    private CompensationRequestRepository crr;

    @Override
    @Transactional
    public List<CompensationRequest> findAllCompensationRequest(){
        List<CompensationRequest> cReq = crr.findAll();
        return cReq;
    };

    @Override
    @Transactional
    public CompensationRequest findCompensationRequest(Long compensationRequestId){
        CompensationRequest cReq = crr.findById(compensationRequestId).orElse(null);
        return cReq;
    };

    @Override
    @Transactional
    public CompensationRequest createCompensationRequest(CompensationRequest compensationRequest){
        return crr.saveAndFlush(compensationRequest);
    };

    @Override
    @Transactional
    public CompensationRequest updCompensationRequest(CompensationRequest compensationRequest){

        return crr.saveAndFlush(compensationRequest);
    };

    @Override
    @Transactional
    public void removeCompensationRequest(CompensationRequest compensationRequest){
        crr.delete(compensationRequest);
    };
}
