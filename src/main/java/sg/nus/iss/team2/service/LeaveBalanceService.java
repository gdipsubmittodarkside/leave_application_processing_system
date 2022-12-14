package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {

    List<LeaveBalance> findAllLeaveBalance();

    LeaveBalance findLeaveBalance(Long lbid);

    LeaveBalance createLeaveBalance(LeaveBalance leaveBalance);

    LeaveBalance updateLeaveBalance(LeaveBalance leaveBalance);

    void  remove(LeaveBalance leaveBalance);

    // After Manager approve leave. This will minus duration and update new Leave Balance
    void minusAnnualLeaveBalance(LeaveBalance leaveBalance, int days);

    void minusMedicalLeaveBalance(LeaveBalance leaveBalance, int days);

    void minusCompensationLeaveBalance(LeaveBalance leaveBalance, int days);

    // After Manager approve compensation request. Increase compensation leave balance.
    void addCompensationLeaveBalance(LeaveBalance leaveBalance, double days);
}
