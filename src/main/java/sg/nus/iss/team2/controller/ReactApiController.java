package sg.nus.iss.team2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.EmployeeModel;
import sg.nus.iss.team2.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class ReactApiController {
    private final EmployeeService empService;

    public ReactApiController(EmployeeService empService) {
        this.empService = empService;
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return empService.createEmployee(employee);
    }

    @GetMapping("/employee")
    public List<EmployeeModel> getAllLeaves() {
        List<Employee> employees = empService.findAllEmployee();
        List<EmployeeModel> empMol = employees.stream().map(emp -> new EmployeeModel(emp.getEmployeeId(),
                emp.getName(), emp.getStatus(), emp.getManagerId())).collect(Collectors.toList());
        return empMol;
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long employeeId){
        boolean deleted = false;
        deleted = empService.deleteEmployee(employeeId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long employeeId){
        EmployeeModel empMol = new EmployeeModel();
        Employee employee = empService.findEmployeeById(employeeId);
        BeanUtils.copyProperties(employee,empMol);
        return ResponseEntity.ok(empMol);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long employeeId,
                                                    @RequestBody EmployeeModel empMol){
        empMol = empService.updateEmployeeMol(employeeId,empMol);
        return ResponseEntity.ok(empMol);
    }

}
