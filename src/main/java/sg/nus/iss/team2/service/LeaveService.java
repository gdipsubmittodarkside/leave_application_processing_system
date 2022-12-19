package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;

public interface LeaveService {
    List<Leave> findAllLeaves();

    List<Leave> findEmployeeLeaves(Employee employee);

    Leave findLeave(Long lid);

    Leave createLeave(Leave leave);

    Leave updateLeave(Leave leave);

    void removeLeave(Leave leave);

    Boolean isOutOfLeave(Leave leave, Employee emp);

    List<Leave> findTeamLeaveHistory(List<Employee> team);


    List<Leave> findLeavePendingApproval(List<Employee> team);

    void updateLeaveAndLeaveBalance(Leave leave, String decision);

}
