package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.LeaveType;
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.repository.LeaveBalanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {
    @Resource

    private LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    LeaveTypeService leaveTypeService;

    @Override
    @Transactional
    public List<LeaveBalance> findAllLeaveBalance() {
        return leaveBalanceRepository.findAll();
    }

    @Override
    @Transactional
    public LeaveBalance findLeaveBalance(Long lbid) {
        return leaveBalanceRepository.findById(lbid).orElse(null);
    }

    @Override
    @Transactional
    public LeaveBalance findEmployeeLeaveBalance(Employee emp) {
        return leaveBalanceRepository.findEmployeeLeaveBalance(emp);
    }

    @Override
    @Transactional
    public LeaveBalance createLeaveBalance(LeaveBalance leaveBalance) {
        return leaveBalanceRepository.saveAndFlush(leaveBalance);
    }

    @Override
    @Transactional
    public LeaveBalance updateLeaveBalance(LeaveBalance leaveBalance) {
        return leaveBalanceRepository.saveAndFlush(leaveBalance);
    }

    @Override
    @Transactional
    public void remove(LeaveBalance leaveBalance) {
        leaveBalanceRepository.delete(leaveBalance);
    }

    @Override
    @Transactional
    public void minusAnnualLeaveBalance(LeaveBalance leaveBalance, int days){
        int curr_annualBalance = leaveBalance.getBalanceAnnualLeaveDays();
        int new_annualBalance = curr_annualBalance - days;
        leaveBalance.setBalanceAnnualLeaveDays(new_annualBalance);
        
        updateLeaveBalance(leaveBalance);
    }

    @Override
    @Transactional
    public void minusMedicalLeaveBalance(LeaveBalance leaveBalance, int days){
        int curr_medicalBalance = leaveBalance.getBalanceMedicalLeaveDays();
        int new_medicalBalance = curr_medicalBalance - days;
        leaveBalance.setBalanceMedicalLeaveDays(new_medicalBalance);
        
        updateLeaveBalance(leaveBalance);
    }

    @Override
    @Transactional
    public void minusCompensationLeaveBalance(LeaveBalance leaveBalance, double days){
        double curr_compBalance = leaveBalance.getBalanceCompensationLeaveDays();
        double new_compBalance = curr_compBalance - days;
        leaveBalance.setBalanceCompensationLeaveDays(new_compBalance);

        updateLeaveBalance(leaveBalance);
    }

    @Override
    @Transactional
    public void addCompensationLeaveBalance(LeaveBalance leaveBalance, double days){
        double curr_compBalance = leaveBalance.getBalanceCompensationLeaveDays();
        double new_compBalance = curr_compBalance + days;
        leaveBalance.setBalanceCompensationLeaveDays(new_compBalance);

        updateLeaveBalance(leaveBalance);
    }

    @Override
    @Transactional
    public void addAnnualLeaveBalance(LeaveBalance leaveBalance, int days){
        int curr_compBalance = leaveBalance.getBalanceAnnualLeaveDays();
        int new_compBalance = curr_compBalance + days;
        leaveBalance.setBalanceAnnualLeaveDays(new_compBalance);

        updateLeaveBalance(leaveBalance);
    }

    @Override
    @Transactional
    public void addMedicalLeaveBalance(LeaveBalance leaveBalance, int days){
        int curr_compBalance = leaveBalance.getBalanceMedicalLeaveDays();
        int new_compBalance = curr_compBalance + days;
        leaveBalance.setBalanceMedicalLeaveDays(new_compBalance);

        updateLeaveBalance(leaveBalance);
    }

    // YT Added
    @Override
    @Transactional
    public LeaveBalance createDefaultForNewUser(User userFromDB){
        List<String> roleList = userFromDB.getRoleNames();

       List<LeaveType> leaveTypes = leaveTypeService.findAllLeaveType();

       int annual_admin = leaveTypes.stream().filter(l -> l.getLeaveTypeName().equals("annual_admin")).findAny().get().getLeavePeriod();
       int annual_staff = leaveTypes.stream().filter(l -> l.getLeaveTypeName().equals("annual_staff")).findAny().get().getLeavePeriod();
       int medical = leaveTypes.stream().filter(l -> l.getLeaveTypeName().equals("medical")).findAny().get().getLeavePeriod();

        if (roleList.contains("admin") && !roleList.contains("manager") && !roleList.contains("staff")){
            
            LeaveBalance newLB = new LeaveBalance(annual_admin, medical, 0.0);
            createLeaveBalance(newLB);
            return newLB;

        }
        else{
            LeaveBalance newLB2 = new LeaveBalance(annual_staff, medical, 0.0);
            createLeaveBalance(newLB2);
            return newLB2;
        }
    }
}
