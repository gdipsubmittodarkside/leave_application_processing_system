package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.User;

import java.util.List;

public interface LeaveBalanceService {

    List<LeaveBalance> findAllLeaveBalance();

    LeaveBalance findEmployeeLeaveBalance(Employee emp);

    LeaveBalance findLeaveBalance(Long lbid);

    LeaveBalance createLeaveBalance(LeaveBalance leaveBalance);

    LeaveBalance updateLeaveBalance(LeaveBalance leaveBalance);

    void  remove(LeaveBalance leaveBalance);

    // After Manager approve leave. This will minus duration and update new Leave Balance
    void minusAnnualLeaveBalance(LeaveBalance leaveBalance, int days);

    void minusMedicalLeaveBalance(LeaveBalance leaveBalance, int days);

    void minusCompensationLeaveBalance(LeaveBalance leaveBalance, double days);

    // After Manager approve compensation request. Increase compensation leave balance.
    void addCompensationLeaveBalance(LeaveBalance leaveBalance, double days);

    void addAnnualLeaveBalance(LeaveBalance leaveBalance, int days);

    void addMedicalLeaveBalance(LeaveBalance leaveBalance, int days);

    // for new user (employee object created first)
    LeaveBalance createDefaultForNewUser(User userFromDB);
}
