package sg.nus.iss.team2.service;

import sg.nus.iss.team2.Utility.Calculate;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.repository.LeaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveRepository leaveRepository;

    @Autowired
    Calculate calculate;

    @Override
    @Transactional
    public List<Leave> findAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public List<Leave> findEmployeeLeaves(Employee employee){
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
    public Boolean isOutOfLeave(Leave leave, LeaveBalance lb){
        String leaveType = leave.getLeaveType().toString();
        LocalDate startDate = leave.getStartDate();
        LocalDate endDate = leave.getEndDate();

        double leaveDuration = calculate.numOfDaysMinusPHAndWeekend(startDate, endDate);
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

}
