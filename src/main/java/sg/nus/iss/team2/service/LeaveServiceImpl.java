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

//NEWCHANGES
@Service
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveRepository leaveRepository;

    @Autowired
    private Calculate calculate;

    @Autowired
    LeaveBalanceService leaveBalanceService;

    @Autowired
    private LeaveBalanceService LBservice;


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
    public List<Leave> findEmployeeThisYearLeaves(Employee employee) {

        int thisyear = LocalDate.now().getYear();
        List<Leave> thisyearLeaves = new ArrayList<>();
        for(Leave lv : leaveRepository.findEmployeeLeave(employee)){
            if(lv.getEndDate().getYear() >= thisyear){
                thisyearLeaves.add(lv);
            }
        }
        return thisyearLeaves;
    };

    @Override
    @Transactional
    public Leave findLeave(Long lid) {
        return leaveRepository.findById(lid).orElse(null);
    }

    @Override
    @Transactional
    public Leave createLeave(Leave leave) {
        this.deductLeaveBalance(leave, leave.getEmployee());
        return leaveRepository.saveAndFlush(leave);
    }

    @Override
    @Transactional
    public Leave updateLeave(Leave leave) {
        return leaveRepository.saveAndFlush(leave);
    }

    @Override
    @Transactional
    public Leave cancelLeave(Leave leave) {
        leave.setStatus(LeaveStatusEnum.CANCELLED);
        this.addLeaveBalance(leave, leave.getEmployee());
        return leaveRepository.saveAndFlush(leave);
    }

    @Override
    @Transactional
    public void removeLeave(Leave leave) {
        leaveRepository.delete(leave);
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
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

            // update "LeaveBalance" DB with new (increased) leave balance
            Employee emp = leave.getEmployee();
            this.addLeaveBalance(leave, emp);
            
        }
        else if (decision.equalsIgnoreCase(LeaveStatusEnum.APPROVED.toString()) )
        {
            // update Leave item with new status; leave balance already subtracted when employee apply for leave
            leave.setStatus(LeaveStatusEnum.APPROVED);
        }
        
        leave.setComment(comment);
        updateLeave(leave);
    }

    @Override
    @Transactional
    public void deductLeaveBalance(Leave leave, Employee emp){
            LeaveBalance LB1 = emp.getLeaveBalance();
            String typeOfLeave = leave.getLeaveType().toString();
            
            LocalDate startDate = leave.getStartDate();
            LocalDate endDate = leave.getEndDate();
            double durationInDays = calculate.numOfDaysMinusPHAndWeekend(startDate, endDate);


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

    @Override
    @Transactional
    public void addLeaveBalance(Leave leave, Employee emp){
        LeaveBalance LB1 = emp.getLeaveBalance();
        String typeOfLeave = leave.getLeaveType().toString();
        
        LocalDate startDate = leave.getStartDate();
        LocalDate endDate = leave.getEndDate();
        double durationInDays = calculate.numOfDaysMinusPHAndWeekend(startDate, endDate);


        if (typeOfLeave.equals("ANNUAL")){
            
            int INTdurationInDays = (int) durationInDays;
            LBservice.addAnnualLeaveBalance(LB1, INTdurationInDays);
        }
        else if (typeOfLeave.equals("MEDICAL"))
        {
            int INTdurationInDays = (int) durationInDays;
            LBservice.addMedicalLeaveBalance(LB1, INTdurationInDays);
        }
        else if (typeOfLeave.equals("COMPENSATION"))
        {
            LBservice.addCompensationLeaveBalance(LB1, durationInDays);
        }

    }




    @Override
    @Transactional
    public Leave findOverlapLeave(Leave leave, Employee emp){
        List<Leave> leaves = findEmployeeLeaves(emp);
        for(Leave lv: leaves){
            if(isDateOverlap(lv,leave)){
                return lv;
            }
        }
        return null;
    }

    public boolean isDateOverlap(Leave existing, Leave newLeave){
        LocalDate s1 = existing.getStartDate();
        LocalDate e1 = existing.getEndDate();
        LocalDate s2 = newLeave.getStartDate();
        LocalDate e2 = newLeave.getEndDate();
        
        if((s1.isBefore(s2) && e1.isAfter(s2)) ||
        (s1.isBefore(e2) && e1.isAfter(e2)) ||
        (s1.isBefore(s2) && e1.isAfter(e2)) ||
        (s1.isAfter(s2) && e1.isBefore(e2)) ||
        s1.isEqual(s2) || s1.isEqual(e2) || e1.isEqual(s2) || e1.isEqual(e2) ){
            return true;
        }


        return false;
    }
    
    // scheduler.schedule(task, new CronTrigger("0 15 9-17 * * MON-FRI"));

}
