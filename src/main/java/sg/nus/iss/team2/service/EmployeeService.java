package sg.nus.iss.team2.service;

import sg.nus.iss.team2.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findEmployeeById(Long id);

    List<Employee> findSubordinates(Long id);


}
