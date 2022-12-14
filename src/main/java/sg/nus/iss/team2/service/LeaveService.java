package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Approve;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> findAllLeaves();

    Leave findLeave(Long lid);

    Leave createLeave(Leave leave);

    Leave updateLeave(Leave leave);

    void removeLeave(Leave leave);

    // For Manager Controller
    List<Leave> findTeamLeaveHistory(List<Employee> team);

    List<Leave> findLeavePendingApproval(List<Employee> team);

    void updateLeaveAndLeaveBalance(Leave leave, String decision);



}
