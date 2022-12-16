package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;
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
}
