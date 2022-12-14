package sg.nus.iss.team2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.LeaveType;
import sg.nus.iss.team2.model.Role;
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.service.EmployeeService;
import sg.nus.iss.team2.service.LeaveTypeService;
import sg.nus.iss.team2.service.RoleService;
import sg.nus.iss.team2.service.UserService;
import sg.nus.iss.team2.validator.LeaveTypeValidator;
import sg.nus.iss.team2.validator.UserCreateValidator;

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
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService empService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateValidator userCreateValidator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LeaveTypeService leTyService;

    @InitBinder("user")
    private void initUserBinder(WebDataBinder binder) {
        binder.addValidators(userCreateValidator);
    }

    @Autowired
    private LeaveTypeValidator ltValidator;

    @InitBinder("leaveType")
    private void initLeaveTypeBinder(WebDataBinder binder) {
        binder.addValidators(ltValidator);
    }

    /*
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>MANAGE EMPLOYEE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */

    @RequestMapping("/employee/list")
    public String EmloyeeList(Model model) {
        return findPaginated(1, "employeeId", "asc", 5, model);
    }

    @GetMapping("/employee/list/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            @RequestParam("pageSize") int pageSize,
            Model model) {
        Page<Employee> page = empService.findPaginated(pageNo, pageSize, sortField,
                sortDir);
        List<Employee> employees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("employee/create")
    public String newEmployeePage(Model model) {

        model.addAttribute("employee", new Employee());
        model.addAttribute("eidlist", empService.findAllUserIDs());
        return "employee-new";
    }

    @PostMapping("employee/create")
    public String newEmployeeSave(@ModelAttribute @Valid Employee employee,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("eidlist", empService.findAllUserIDs());
            return "employee-new";
        }
        empService.createEmployee(employee);
        return "redirect:/admin/employee/list";
    }

    @RequestMapping("employee/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Employee emp = empService.findEmployeeById(id);
        model.addAttribute("employee", emp);
        model.addAttribute("eidlist", empService.findAllUserIDs());
        return "employee-edit";
    }

    @PostMapping("employee/edit/{id}")
    public String editUser(@ModelAttribute @Valid Employee emp, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("eidlist", empService.findAllUserIDs());
            return "employee-edit";
        }
        empService.updateEmployee(emp);
        return "redirect:/admin/employee/list";
    }

    @RequestMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Employee emp = empService.findEmployeeById(id);
        empService.resignedEmployee(emp);
        return "redirect:/admin/employee/list";
    }

    /*
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>MANAGE User<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */
    @RequestMapping("/user/list")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "user-list";
    }

    @GetMapping("/user/create")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        List<Employee> eidList = empService.findAllEmployee();

        model.addAttribute("eidlist", eidList);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        return "user-new";
    }

    @PostMapping("/user/create")
    public String createNewUserPage(@ModelAttribute @Valid User user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            List<Employee> eidList = empService.findAllEmployee();
            model.addAttribute("eidlist", eidList);
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);
            return "user-new";
        }
        userService.createUser(user);
        List<Role> newRoleSet = new ArrayList<>();
        user.getRoles().forEach(role -> {
            Role complete = roleService.findRoleById(role.getRoleId());
            newRoleSet.add(complete);
        });
        user.setRoles(newRoleSet);
        userService.createUser(user);

        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-edit";
    }

    @PostMapping("/user/edit/{id}")
    public String editUserSave(@ModelAttribute @Valid User user, BindingResult result,
            @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userService.findUser(id));
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("allRoles", roles);
            return "user-edit";
        }
        userService.updateUser(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findUser(id);
        userService.removeUser(user);

        return "redirect:/admin/user/list";
    }

    /*
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>MANAGE LeaveType<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */

    @GetMapping("/leavetype/list")
    public String LeaveTypeList(Model model) {
        List<LeaveType> list = leTyService.findAllLeaveType();
        model.addAttribute("leaveType", list);
        return "leavetype-list";
    }

    @RequestMapping("/leavetype/create")
    public String createLeaveType(Model model) {
        model.addAttribute("leaveType", new LeaveType());
        return "leavetype-new";
    }

    @PostMapping("/leavetype/create")
    public String createLeaveTypePage(@ModelAttribute @Valid LeaveType leaveType , BindingResult result) {
        if (result.hasErrors()) {
            return "leavetype-new";
        }
        leTyService.createLeaveType(leaveType);
        return "redirect:/admin/leavetype/list";
    }

    @RequestMapping("/leavetype/edit/{leaveTypeName}")
    public String editLeaveTypePage(@PathVariable String leaveTypeName, Model model) {
        LeaveType leaveType = leTyService.findLeaveType(leaveTypeName);
        model.addAttribute("leavetype", leaveType);
        return "leavetype-edit";
    }

    @PostMapping("/leavetype/edit/{leaveTypeName}")
    public String editLeaveSave(@ModelAttribute @Valid LeaveType leaveType, BindingResult result,
            @PathVariable String leaveTypeName) {
        if (result.hasErrors()) {
            return "leavetype-edit";
        }
        leTyService.updateLeaveType(leTyService.findLeaveType(leaveTypeName));
        return "redirect:/admin/leavetype/list";
    }

    @RequestMapping("/leavetype/delete/{leaveTypeName}")
    public String deleteLeaveType(@PathVariable("leaveTypeName") String name) {
        LeaveType leaveType = leTyService.findLeaveType(name);
        leTyService.removeLeaveType(leaveType);
        return "redirect:/admin/leavetype/list";
    }

}
