package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.repository.LeaveBalanceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {
    @Resource

    private LeaveBalanceRepository leaveBalanceRepository;

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
}
