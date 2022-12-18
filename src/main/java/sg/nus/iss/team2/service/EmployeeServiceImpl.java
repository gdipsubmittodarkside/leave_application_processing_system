package sg.nus.iss.team2.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.EmployeeModel;
import sg.nus.iss.team2.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> findAllEmployee() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setStatus("current");
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return employeeRepository.save(employee);
    }

    @Override
    public void resignedEmployee(Employee employee) {
        employee.setStatus("resigned");
        employeeRepository.save(employee);

    }

    @Override
    public List<Long> findAllUserIDs() {
        // TODO Auto-generated method stub
        return employeeRepository.findAllEmployeeIDs();
    }

    @Override
    public boolean deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setStatus("resigned");
        return true;
    }

    @Override
    public EmployeeModel updateEmployeeMol(Long employeeId, EmployeeModel empMol) {
        Employee emp = employeeRepository.findEmployeeByEmployeeId(employeeId);
        emp.setName(empMol.getName());
        emp.setManagerId(empMol.getManagerId());
        employeeRepository.save(emp);
        empMol.setEmployeeId(employeeId);
        return empMol;
    }
}
