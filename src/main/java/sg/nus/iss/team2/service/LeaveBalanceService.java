package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {

    List<LeaveBalance> findAllLeaveBalance();

    LeaveBalance findEmployeeLeaveBalance(Employee emp);

    LeaveBalance findLeaveBalance(Long lbid);

    LeaveBalance createLeaveBalance(LeaveBalance leaveBalance);

    LeaveBalance updateLeaveBalance(LeaveBalance leaveBalance);

    void  remove(LeaveBalance leaveBalance);
}
