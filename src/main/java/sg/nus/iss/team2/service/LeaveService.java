package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;


import java.time.LocalDate;


public interface LeaveService {
    List<Leave> findAllLeaves();

    List<Leave> findEmployeeLeaves(Employee employee);

    Leave findLeave(Long lid);

    Leave createLeave(Leave leave);

    Leave updateLeave(Leave leave);

    Leave cancelLeave(Leave leave);

    void removeLeave(Leave leave);

    Boolean isOutOfLeave(Leave leave, Employee emp);

    List<Leave> findTeamLeaveHistory(List<Employee> team);


    List<Leave> findApprovedTeamLeaveHistory(List<Employee> team, LocalDate theLeaveStartDate, LocalDate theLeaveEndDate);

    List<Leave> findLeavePendingApproval(List<Employee> team);

    void updateLeaveAndLeaveBalance(Leave leave, String decision, String comment);

    void deductLeaveBalance(Leave leave, Employee emp);

    void addLeaveBalance(Leave leave, Employee emp);

    Leave findOverlapLeave(Leave leave, Employee emp);

}
