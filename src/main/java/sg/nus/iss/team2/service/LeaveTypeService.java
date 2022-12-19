package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.LeaveType;

public interface LeaveTypeService {
    List<String> findAllLeaveTypeName();

    LeaveType findLeaveType(String leaveType);

    LeaveType createLeaveType(LeaveType leaveType);

    LeaveType updateLeaveType(LeaveType leaveType);
    
    List<LeaveType> findAllLeaveType();

    void removeLeaveType(LeaveType leaveType);
    
}
