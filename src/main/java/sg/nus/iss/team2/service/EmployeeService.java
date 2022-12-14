package sg.nus.iss.team2.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.nus.iss.team2.model.Employee;

public interface EmployeeService {
    Employee findEmployeeById(Long id);

    List<Employee> findSubordinates(Long id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    List<Employee> findAllEmployee();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void resignedEmployee(Employee employee);

    List<Long> findAllUserIDs();

}
