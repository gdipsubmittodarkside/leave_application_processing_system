package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {

    List<LeaveBalance> findAllLeaveBalance();

    LeaveBalance findLeaveBalance(Integer lbid);

    LeaveBalance createLeaveBalance(LeaveBalance leaveBalance);

    LeaveBalance updateLeaveBalance(LeaveBalance leaveBalance);

    void  remove(LeaveBalance leaveBalance);
}
