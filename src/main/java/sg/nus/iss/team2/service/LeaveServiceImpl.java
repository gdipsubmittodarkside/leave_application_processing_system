package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Approve;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.LeaveStatusEnum;
import sg.nus.iss.team2.model.LeaveTypeEnum;
import sg.nus.iss.team2.repository.LeaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//NEWCHANGES
@Service
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveBalanceService LBservice;

    @Override
    @Transactional
    public List<Leave> findAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public Leave findLeave(Long lid) {
        return leaveRepository.findById(lid).orElse(null);
    }

    @Override
    @Transactional
    public Leave createLeave(Leave leave) {
        return leaveRepository.saveAndFlush(leave);
    }

    @Override
    @Transactional
    public Leave updateLeave(Leave leave) {
        return leaveRepository.saveAndFlush(leave);
    }

    @Override
    @Transactional
    public void removeLeave(Leave leave) {
        leaveRepository.delete(leave);
    }

    @Override
    @Transactional
    public List<Leave> findTeamLeaveHistory(List<Employee> team){
        // Method 1
        List<Leave> teamLeaves = new ArrayList<Leave>();
        
        for (Employee e : team){
            Long emp_id = e.getEmployeeId();
            for (Leave l : leaveRepository.findLeaveHistoryByEmloyeeId(emp_id)){
                teamLeaves.add(l);
            }
        }
        
        return teamLeaves; 
        
        // // Method 2
        // List<Long> all_team_ids = team.stream().map(Employee::getEmployeeId).collect(Collectors.toList());
        // return leaveRepository.findAllById(all_team_ids);
    }


    @Override
    @Transactional
    public List<Leave> findApprovedTeamLeaveHistory(List<Employee> team, LocalDate theLeaveStartDate, LocalDate theLeaveEndDate){
        // Method 1
        List<Leave> teamLeaves = new ArrayList<Leave>();
        
        for (Employee e : team){
            Long emp_id = e.getEmployeeId();
            for (Leave l : leaveRepository.findApprovedLeaveHistoryByEmloyeeId(emp_id, theLeaveStartDate, theLeaveEndDate)){
                teamLeaves.add(l);
            }
        }
        
        return teamLeaves; 
        
    }


    @Override
    @Transactional
    public List<Leave> findLeavePendingApproval(List<Employee> team){
        List<Leave> teamLeavesPendingApproval = new ArrayList<Leave>();
        
        for (Employee e : team){
            Long emp_id = e.getEmployeeId();
            for (Leave l : leaveRepository.findAppliedAndUpdatedLeavesByEmloyeeId(emp_id)){
                teamLeavesPendingApproval.add(l);
            }
        }
        
        return teamLeavesPendingApproval;
    }

    @Override
    @Transactional
    public void updateLeaveAndLeaveBalance(Leave leave, String decision, String comment){


        if (comment!=null) //NEWCHANGES
        {
            leave.setComment(comment);
        }

        if (decision!=null)
        {
            if (decision.equalsIgnoreCase(LeaveStatusEnum.REJECTED.toString()) )
            {
                leave.setStatus(LeaveStatusEnum.REJECTED);
                
            }
            else if (decision.equalsIgnoreCase(LeaveStatusEnum.APPROVED.toString()) )
            {
                // update Leave item with new status
                leave.setStatus(LeaveStatusEnum.APPROVED);
    
                // update "LeaveBalance" DB with new (decreased) leave balance
                Employee emp = leave.getEmployee();
                LeaveBalance LB1 = emp.getLeaveBalance();
                String typeOfLeave = leave.getLeaveType().toString();
                int durationInDays = 5;
    
                if (typeOfLeave.equals("ANNUAL")){
                    
                    LBservice.minusAnnualLeaveBalance(LB1, durationInDays);
                }
                else if (typeOfLeave.equals("MEDICAL"))
                {
                    LBservice.minusMedicalLeaveBalance(LB1, durationInDays);
                }
                else if (typeOfLeave.equals("COMPENSATION"))
                {
                    LBservice.minusCompensationLeaveBalance(LB1, durationInDays);
                }
            }
    
            updateLeave(leave);

        }
        


    }
}
