package sg.nus.iss.team2.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.nus.iss.team2.model.Employee;

public interface EmployeeService {

	List <Employee> getAllEmployees();      //getall method
	
	void saveEmployee(Employee employee);  //save method

	Employee getEmployeeById(long id);   //getbyid method
	
	void deleteEmployeeById(long id);   //delete method
	
	Page <Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);   //using pagination API, must be sortDirection
}
