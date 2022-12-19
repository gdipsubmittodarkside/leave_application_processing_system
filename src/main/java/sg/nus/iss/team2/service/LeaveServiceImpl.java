package sg.nus.iss.team2.service;

import sg.nus.iss.team2.Utility.Calculate;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;

import sg.nus.iss.team2.model.LeaveStatusEnum;

import sg.nus.iss.team2.repository.LeaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;
import javax.transaction.Transactional;


import java.time.LocalDate;
import java.util.*;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.LeaveStatusEnum;
import sg.nus.iss.team2.repository.LeaveRepository;



//NEWCHANGES
@Service
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveRepository leaveRepository;

    @Autowired

    Calculate calculate;

    @Autowired
    LeaveBalanceService leaveBalanceService;

    private LeaveBalanceService LBservice;

    @Autowired
    private Calculate calculator;


    @Override
    public List<Leave> findAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public List<Leave> findEmployeeLeaves(Employee employee) {
        return leaveRepository.findEmployeeLeave(employee);
    };

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
        


    public Boolean isOutOfLeave(Leave leave, Employee emp){
        String leaveType = leave.getLeaveType().toString();
        LocalDate startDate = leave.getStartDate();
        LocalDate endDate = leave.getEndDate();
        double leaveDuration = calculate.numOfDaysMinusPHAndWeekend(startDate, endDate);

        LeaveBalance lb = leaveBalanceService.findEmployeeLeaveBalance(emp);
        int annualBalance = lb.getBalanceAnnualLeaveDays();
        int medicalBalance = lb.getBalanceMedicalLeaveDays();
        double compensationBalance = lb.getBalanceCompensationLeaveDays();

        if (leaveType.equals("ANNUAL")){
                
            int duration = (int) leaveDuration;
            if(duration > annualBalance){
                return true;
            }
            
        }
        else if (leaveType.equals("MEDICAL"))
        {
            int duration = (int) leaveDuration;
            if(duration > medicalBalance){
                return true;
            }
        }
        else if (leaveType.equals("COMPENSATION"))
        {
            if(leaveDuration > compensationBalance){
                return true;
            }
        }

        return false;
    }


    public List<Leave> findTeamLeaveHistory(List<Employee> team) {
        // Method 1

        List<Leave> teamLeaves = new ArrayList<Leave>();

        for (Employee e : team) {
            Long emp_id = e.getEmployeeId();
            for (Leave l : leaveRepository.findLeaveHistoryByEmloyeeId(emp_id)) {
                teamLeaves.add(l);
            }
        }

        
        return teamLeaves; 
    }


    @Override
    @Transactional
    public List<Leave> findApprovedTeamLeaveHistory(List<Employee> team, LocalDate theLeaveStartDate, LocalDate theLeaveEndDate){
        
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
    public List<Leave> findLeavePendingApproval(List<Employee> team) {
        List<Leave> teamLeavesPendingApproval = new ArrayList<Leave>();

        for (Employee e : team) {
            Long emp_id = e.getEmployeeId();
            for (Leave l : leaveRepository.findAppliedAndUpdatedLeavesByEmloyeeId(emp_id)) {
                teamLeavesPendingApproval.add(l);
            }
        }

        return teamLeavesPendingApproval;
    }

    @Override
    @Transactional

    public void updateLeaveAndLeaveBalance(Leave leave, String decision, String comment){

        // if (comment!=null) 
        // {
        //     leave.setComment(comment);
        // }

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
            
            LocalDate startDate = leave.getStartDate();
            LocalDate endDate = leave.getEndDate();
            double durationInDays = calculator.numOfDaysMinusPHAndWeekend(startDate, endDate);


            if (typeOfLeave.equals("ANNUAL")){
                
                int INTdurationInDays = (int) durationInDays;
                LBservice.minusAnnualLeaveBalance(LB1, INTdurationInDays);
            }
            else if (typeOfLeave.equals("MEDICAL"))
            {
                int INTdurationInDays = (int) durationInDays;
                LBservice.minusMedicalLeaveBalance(LB1, INTdurationInDays);
            }
            else if (typeOfLeave.equals("COMPENSATION"))
            {
                LBservice.minusCompensationLeaveBalance(LB1, durationInDays);
            }
        }
        
        leave.setComment(comment);
        updateLeave(leave);
    }

}
