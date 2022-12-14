package sg.nus.iss.team2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveStatusEnum;
import sg.nus.iss.team2.model.LocalDateTimeHandler;
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.service.CompensationRequestService;
import sg.nus.iss.team2.service.LeaveService;
import sg.nus.iss.team2.service.UserService;


/**
 * Personal leave details and application
 *
 * Staff (+ Manager)
 * Form Leave application
 * View personal leave history
 * View personal leave applications
 * Form compensation leave application
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private CompensationRequestService crService;

    @InitBinder("compensation")
  private void initCourseBinder(WebDataBinder binder) {
    // binder.addValidators(courseValidator);
  }
    

    @GetMapping(value = "/viewLeave")
    public String viewLeave(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        List<Leave> leaves = leaveService.findEmployeeLeaves(emp);
        model.addAttribute("leaves", leaves);
        List<CompensationRequest> crReq = crService.findEmployeeCompensationRequest(emp);
        model.addAttribute("crReq", crReq);

        return "viewLeave";
 
    }

    @GetMapping(value = "/claimLeave")
    public String claimLeave(Model model){
        model.addAttribute("compen", new LocalDateTimeHandler());

        return "claimLeave";
    }

    @PostMapping(value = "/claimLeave")
    public String claimCompensation(@ModelAttribute LocalDateTimeHandler compen, BindingResult result,
    HttpSession httpSession){
        if (result.hasErrors()) {
            return "claimLeave";
          }
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
          User user = (User) httpSession.getAttribute("userSession");
          Employee emp = user.getEmployee();
          CompensationRequest compensation = new CompensationRequest();
          compensation.setEmployee(emp);
          compensation.setStatus(LeaveStatusEnum.APPLIED);
          compensation.setOTstartTime(LocalDateTime.parse(compen.getStartTime(), formatter));
          compensation.setOTendTime(LocalDateTime.parse(compen.getEndTime(), formatter));

        crService.createCompensationRequest(compensation);
        String message = "New Compensation Leave Request " + compensation.getCompensationLeaveId() + " was successfully created.";
        System.out.println(message);

        return "redirect:/staff/viewLeave";
    }


    



}
