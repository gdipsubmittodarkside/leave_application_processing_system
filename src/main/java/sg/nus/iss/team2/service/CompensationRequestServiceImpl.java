package sg.nus.iss.team2.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;


import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.LeaveStatusEnum;
import sg.nus.iss.team2.model.LeaveTypeEnum;

import sg.nus.iss.team2.repository.CompensationRequestRepository;

@Service
public class CompensationRequestServiceImpl implements CompensationRequestService {

    @Resource
    private CompensationRequestRepository crr;

    @Autowired
    LeaveBalanceService LBservice;

    @Override
    @Transactional
    public List<CompensationRequest> findAllCompensationRequest(){
        List<CompensationRequest> cReq = crr.findAll();
        return cReq;
    };

    @Override
    @Transactional
    public List<CompensationRequest> findEmployeeCompensationRequest(Employee employee){
        List<CompensationRequest> cReq = crr.findEmployeeCompensationRequest(employee);
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

    @Override
    @Transactional
    public List<CompensationRequest> findRequestByTeam(List<Employee> team){
        List<CompensationRequest> teamRequest = new ArrayList<CompensationRequest>();
        
        for (Employee e : team){
            Long emp_id = e.getEmployeeId();
            List<CompensationRequest> requests = crr.findRequestByEmpId(emp_id);
            if (requests != null || !requests.isEmpty()){
                for (CompensationRequest c : requests){
                    teamRequest.add(c);
                }
            }
        }
        
        return teamRequest;
    }

    @Override
    @Transactional
    public List<CompensationRequest> findTeamCompensationHistory(List<Employee> team){
        List<CompensationRequest> teamCompHistory = new ArrayList<CompensationRequest>();
        
        for (Employee e : team){
            Long emp_id = e.getEmployeeId();
            List<CompensationRequest> compHistory = crr.findHistoryByEmpId(emp_id);
            if (compHistory != null || !compHistory.isEmpty()){
                for (CompensationRequest c : compHistory){
                    teamCompHistory.add(c);
                }
            }
        }
        
        return teamCompHistory;
    }

    @Override
    @Transactional
    public void updateCompReqAndLeaveBalance(CompensationRequest compensationRequest, String decision, String comment){
        
        if (comment!=null)
        {
            compensationRequest.setComment(comment);
        }
        if (decision!=null)
        {
            if (decision.equalsIgnoreCase(LeaveStatusEnum.REJECTED.toString()))
            {
                compensationRequest.setStatus(LeaveStatusEnum.REJECTED);
            }
            else
            {
                // update Compensation item with new status
                compensationRequest.setStatus(LeaveStatusEnum.APPROVED);
    
                // update "LeaveBalance" DB with new (increased) compensation leave balance
                Employee emp = compensationRequest.getEmployee();
                LeaveBalance LB1 = emp.getLeaveBalance();
                double durationInDays = 2;
    
                LBservice.addCompensationLeaveBalance(LB1, durationInDays);
            }
    
            updCompensationRequest(compensationRequest);

        }

    }

}
