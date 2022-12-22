package sg.nus.iss.team2.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.reporting.FilesExporter;
import sg.nus.iss.team2.service.CompensationRequestService;
import sg.nus.iss.team2.service.LeaveService;
import sg.nus.iss.team2.validator.CompensationRequestValidator;
import sg.nus.iss.team2.validator.LeaveValidator;


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

    @Autowired
    private LeaveValidator leaveValidator;
    
    @Autowired
    private CompensationRequestValidator crValidator;

    @Autowired
    private FilesExporter export;

    @InitBinder("compensation")
    private void initcrBinder(WebDataBinder binder){
        binder.addValidators(crValidator);
    }

    @InitBinder("leave")
    private void initLeaveBinder(WebDataBinder binder){
        binder.addValidators(leaveValidator);
    }

     //<<<View Section>>>

    @GetMapping(value = "/viewLeave")
    public String viewLeave(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        List<Leave> leaves = leaveService.findEmployeeLeaves(emp);
        int thisyear = LocalDate.now().getYear();
        List<Leave> thisyearLeaves = new ArrayList<>();
        for(Leave lv : leaves){
            if(lv.getEndDate().getYear() == thisyear){
                thisyearLeaves.add(lv);
            }
        }
        model.addAttribute("leaves", thisyearLeaves);
        List<CompensationRequest> crReq = crService.findEmployeeCompensationRequest(emp);
        model.addAttribute("crReq", crReq);
        LocalDate today = LocalDate.now();
        model.addAttribute("todayDate", today);

        return "viewLeave";
 
    }
    
    @GetMapping(value = "/viewClaim")
    public String viewClaim(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        List<Leave> leaves = leaveService.findEmployeeLeaves(emp);
        model.addAttribute("leaves", leaves);
        List<CompensationRequest> crReq = crService.findEmployeeCompensationRequest(emp);
        model.addAttribute("crReq", crReq);
        LocalDate today = LocalDate.now();
        model.addAttribute("todayDate", today);

        return "view-Claim";
 
    }

    @GetMapping(value={"/leaveDetail/{id}"})
    public String showLeaveDetail(@PathVariable Long id, Model model){
        Leave targetLeave = leaveService.findLeave(id);
        model.addAttribute("leaveDetail", targetLeave);
        return "leaveDetail";
    }
    
    @GetMapping(value={"/compensationRequestDetail/{id}"})
    public String showCompensationLeaveDetail(@PathVariable Long id, Model model){
        CompensationRequest target = crService.findCompensationRequest(id);
        model.addAttribute("compensationRequestDetail", target);
        return "compensationRequestDetail";
    }

    //<<<Compensation Request Section>>>

    @GetMapping(value = "compensation/claimLeave")
    public String claimLeave(Model model){
        model.addAttribute("compensation", new CompensationRequest());

        return "claimLeave";
    }

    @PostMapping(value = "compensation/claimLeave")
    public String claimCompensation( @Valid @ModelAttribute(value="compensation") CompensationRequest compensationRequest, BindingResult result,
    HttpSession httpSession){
        if (result.hasErrors()) {
            return "claimLeave";
          }
          User user = (User) httpSession.getAttribute("userSession");
          Employee emp = user.getEmployee();
          compensationRequest.setEmployee(emp);
          compensationRequest.setStatus(LeaveStatusEnum.APPLIED);

        crService.createCompensationRequest(compensationRequest);
        String message = "New Compensation Leave Request " + compensationRequest.getCompensationLeaveId() + " was successfully created.";
        System.out.println(message);

        return "redirect:/staff/viewClaim";
    }

    @GetMapping(value={"/compensation/edit/{id}"})
    public String editCompensationRequest(@PathVariable Long id, Model model){
        CompensationRequest targetRequest = crService.findCompensationRequest(id);
        model.addAttribute("compensation", targetRequest);
        return "editClaimLeave";
    }

    @GetMapping("/exportClaimHiscsv")
    public void exportClaimHisToCSV(HttpServletResponse response, HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        List<CompensationRequest> crReq = crService.findEmployeeCompensationRequest(emp);
        export.exportCompensationHisToCSV(crReq, response);
    }

    @GetMapping("/exportLeaveHiscsv")
    public void exportLeaveHisToCSV(HttpServletResponse response, HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        List<Leave> leaves = leaveService.findEmployeeLeaves(emp);
        export.exportLeaveHisToCSV(leaves, response);
    }
    @PostMapping(value={"/compensation/edit/{id}"})
    public String updateCompensationRequest(@PathVariable Long id, @Valid @ModelAttribute(value="compensation") CompensationRequest compensation, BindingResult bindingResult, Model model){
       
        if (bindingResult.hasErrors()) {
            return "editClaimLeave";
          }

          CompensationRequest exitingRequest = crService.findCompensationRequest(id);
          exitingRequest.setOTstartTime(compensation.getOTstartTime());
          exitingRequest.setOTendTime(compensation.getOTendTime());
          exitingRequest.setDescription(compensation.getDescription());
          exitingRequest.setStatus(LeaveStatusEnum.UPDATED);

        crService.updCompensationRequest(exitingRequest);
        return "redirect:/staff/viewClaim";
    }

    @GetMapping(value={"/compensation/cancel/{id}"})
    public String cancelCompensationRequest(@PathVariable Long id){
        CompensationRequest targetCompensationRequest = crService.findCompensationRequest(id);
        targetCompensationRequest.setStatus(LeaveStatusEnum.CANCELLED);
        crService.updCompensationRequest(targetCompensationRequest);
        return "redirect:/staff/viewClaim";
    }

    //<<<Leave Section>>>

    @GetMapping(value= "/leave/new")
    public String  createLeaveForm(Model model){
        Leave newleave = new Leave();
        model.addAttribute("leave", newleave);
        return "createLeave";
        
    }

    @PostMapping(value= "/leave/submitLeave")
    public String submitLeave(@Valid @ModelAttribute Leave leave, BindingResult result,
    HttpSession httpSession, Model model){
        if (result.hasErrors()) {
            return "createLeave";
        }

        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();

        if(leaveService.isOutOfLeave(leave,emp)){
            model.addAttribute("leaveBalance", emp.getLeaveBalance());
            return "outOfLeave";
        }
        Leave overlapLeave = leaveService.findOverlapLeave(leave, emp);
        if(overlapLeave != null){
            model.addAttribute("overlapLeave", overlapLeave);
            return "overlapLeave";
        }

        leave.setEmployee(emp);
        leave.setStatus(LeaveStatusEnum.APPLIED);
        
        leaveService.createLeave(leave);
        
        return "redirect:/staff/viewLeave";
    }


    @GetMapping(value={"/leave/edit/{id}"})
    public String editLeaveForm(@PathVariable Long id, Model model){
        Leave targetLeave = leaveService.findLeave(id);
        if(LocalDate.now().isAfter(targetLeave.getStartDate())){
            return "OutDated";
        }
        model.addAttribute("leave", targetLeave);
        return "editLeave";
    }

    @PostMapping(value={"/leave/edit/{id}"})
    public String updateLeave(@PathVariable Long id, @Valid @ModelAttribute Leave leave, BindingResult bindingResult, Model model, HttpSession httpSession){
       
        if (bindingResult.hasErrors()) {
            return "editLeave";
          }
          
        User user = (User) httpSession.getAttribute("userSession");
        Employee emp = user.getEmployee();
        
        if(leaveService.isOutOfLeave(leave,emp)){
            model.addAttribute("leaveBalance", emp.getLeaveBalance());
            return "outOfLeave";
        }
        Leave overlapLeave = leaveService.findOverlapLeave(leave, emp);
        if(overlapLeave != null && overlapLeave.getLeaveId() != leave.getLeaveId()){
            model.addAttribute("overlapLeave", overlapLeave);
            return "overlapLeave";
        }

        Leave exitingLeave = leaveService.findLeave(id);
        exitingLeave.setStartDate(leave.getStartDate());
        exitingLeave.setEndDate(leave.getEndDate());
        exitingLeave.setLeaveType(leave.getLeaveType());
        exitingLeave.setReason(leave.getReason());

        leaveService.updateLeave(exitingLeave);
        return "redirect:/staff/viewLeave";
    }
    
    @GetMapping(value={"/delete/{id}"})
    public String cancelLeave(@PathVariable Long id){
        Leave targetLeave = leaveService.findLeave(id);
        if(LocalDate.now().isAfter(targetLeave.getStartDate())){
            return "OutDated";
        }
        
        leaveService.cancelLeave(targetLeave);
        return "redirect:/staff/viewLeave";
    }


}
