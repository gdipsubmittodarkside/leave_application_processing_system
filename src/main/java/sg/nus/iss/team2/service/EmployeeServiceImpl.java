package sg.nus.iss.team2.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.repository.EmployeeRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeRepository employeeRepository;
    @Override
    @Transactional
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeByEmployeeId(id);
    }



    @Override
    @Transactional
    public List<Employee> findSubordinates(Long id) {
        return employeeRepository.findSubordinates(id);
    }
}
