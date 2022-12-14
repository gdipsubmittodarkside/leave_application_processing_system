package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> findAllLeaves();

    List<Leave> findEmployeeLeaves(Employee employee);

    Leave findLeave(Long lid);

    Leave createLeave(Leave leave);

    Leave updateLeave(Leave leave);

    void removeLeave(Leave leave);




}
