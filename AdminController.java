package sg.nus.iss.team2.controller;

/**
 *
 * Admin to manage employees and their leave entitlement
 *
 * Admin
 * View all staff
 * Form add new staff
 * Form delete / modify staff
 * View Leave entitlement
 * Form modify / add / delete Leave Entitlement 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.team2.service.EmployeeService;
import sg.nus.iss.team2.model.Employee;


@Controller
public class AdminController{
	
	@Autowired
	private EmployeeService employeeService;
	
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		//model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return findPaginated(1, "fullName", "asc", model);		//default field is fullName + Ascending
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/";  //home page
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		//get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as model attribute to populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id, Model model) {
		//call delete employee method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";  //home page
	}
	
	@GetMapping("/age/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,   // /page/1?sortField=name&sortDir=
			@RequestParam("sortDir") String sortDir,       // read parameters
			Model model) {
		int pageSize = 5;
		Page <Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List <Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");  //for toggle between 
		
		model.addAttribute("listEmployees", listEmployees);
		
		return "index";
	}
}



