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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value= "/new")
    public String  createLeaveForm(Model model){
        Leave newleave = new Leave();
        model.addAttribute("leave", newleave);
        return "createLeave";
        
    }

    @PostMapping(value= "/submitLeave")
    public String submitLeave(@ModelAttribute Leave leave, BindingResult result,
    HttpSession httpSession){

        if (result.hasErrors()) {
            return "createLeave";
          }
          User user = (User) httpSession.getAttribute("userSession");
          Employee emp = user.getEmployee();

        leave.setEmployee(emp);
        leave.setStatus(LeaveStatusEnum.APPLIED);
        leaveService.createLeave(leave);
        
        return "redirect:/staff/viewLeave";
    }


    @GetMapping(value={"/edit/{id}"})
    public String editLeaveForm(@PathVariable Long id, Model model){
        Leave targetLeave = leaveService.findLeave(id);
        model.addAttribute("targetLeave", leaveService.updateLeave(targetLeave));
        return "editLeave";
    }


    @PostMapping(value={"/{id}"})
    public String updateLeave(@PathVariable Long id,@ModelAttribute("leave") Leave leave, Model model){
     
        Leave exitingLeave = leaveService.findLeave(id);
        exitingLeave.setStartDate(leave.getStartDate());
        exitingLeave.setEndDate(leave.getEndDate());
        exitingLeave.setLeaveType(leave.getLeaveType());
        exitingLeave.setReason(leave.getReason());

        leaveService.updateLeave(exitingLeave);
        return "redirect:/staff/list";
    }
    



}
