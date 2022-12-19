package sg.nus.iss.team2.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.LeaveType;
import sg.nus.iss.team2.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Resource
    private LeaveTypeRepository repo;

    @Override
    public List<String> findAllLeaveTypeName() {
        List<String> leaveTypeName = new ArrayList<>();
        repo.findAll().forEach(x -> leaveTypeName.add(x.getLeaveTypeName()));
        return leaveTypeName;
    }

    @Override
    public LeaveType findLeaveType(String leaveType) {

        return repo.findById(leaveType).orElse(null);
    }

    @Transactional
    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {

        return repo.saveAndFlush(leaveType);
    }

    @Transactional
    @Override
    public LeaveType updateLeaveType(LeaveType leaveType) {

        return repo.save(leaveType);
    }

    @Override
    public List<LeaveType> findAllLeaveType() {

        return repo.findAll();
    }

    @Override
    public void removeLeaveType(LeaveType leaveType) {
        repo.delete(leaveType);
    }

}
