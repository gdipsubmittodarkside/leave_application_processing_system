package sg.nus.iss.team2.controller;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.util.Duration;
import sg.nus.iss.team2.model.*;
import sg.nus.iss.team2.service.*;

/**
 * Manager to approve/reject
 *
 * Manager
 * Form approval/reject subordinate leave application
 * View all subordinate leave history
 * Approve/reject compensation leave
 */

@Controller 
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private LeaveService leaveService;

    @Autowired 
    private HttpSession session;

    @Autowired
    private CompensationRequestService compRequestService;

    @Autowired
    private LeaveBalanceService LBservice;

    // Get the List of Employees under the Manager
    public List<Employee> FindEmployeesUnderManager(){
        User manager = (User)session.getAttribute("userSession");
        Long managerId = manager.getUserId();

        List<Employee> team = empService.findSubordinates(managerId);

        return team;
    }

    // View Employees' Leave History
    @GetMapping("/team-leave")
    public String ViewTeamLeaveHistory(Model model){
        List<Employee> team = FindEmployeesUnderManager();
        List<Leave> teamLeaves = leaveService.findTeamLeaveHistory(team);
            // only: approved, withdraw, rejected 

        model.addAttribute("teamLeaves", teamLeaves);
        return "team-leave";
    }

    // View Pending Approvals (1) leave, (2) claims
    @GetMapping("/pending")
    public String ViewPendingApprovals(Model model){
        List<Employee> team = FindEmployeesUnderManager();

        // Leave - applied & updated only
        List<Leave> leavePendingApproval = leaveService.findLeavePendingApproval(team);
        model.addAttribute("leavePendingApproval", leavePendingApproval);

        // Compensation Claims - applied & updated only
        List<CompensationRequest> compRequests = compRequestService.findRequestByTeam(team);
        model.addAttribute("compRequests", compRequests);

        return "mgr-pending-approval";
    } 

    // when "Detail" button is clicked, display details of 1 particular leave
        // will show approve/reject option
    @PostMapping("/leave/display/{id}")
    public String ViewLeaveDetails(@PathVariable Long id, Model model)
    {
        Leave leave = leaveService.findLeave(id);
        model.addAttribute("leave",leave);
        model.addAttribute("approve", new Approve());

        return "manager-leave-detail";
    }

    // After Manager clicks "approve" or "reject" Leave
    @PostMapping("/leave/edit/{leave_id}")
    public String ApproveOrRejectLeave(Approve approve, @PathVariable long leave_id){

        Leave leave = leaveService.findLeave(leave_id);
        String decision = approve.getDecision();

        leaveService.updateLeaveAndLeaveBalance(leave, decision);

        return "redirect:/manager/pending";
    }

    // when "Detail" button is clicked, display details of 1 particular Claim Request
        // will show approve/reject option
    @PostMapping("/claim/display/{comp_id}")
    public String ViewCompensationRequest(@PathVariable Long comp_id, Model model)
    {
        CompensationRequest compReq = compRequestService.findCompensationRequest(comp_id);
        
        model.addAttribute("compRequest", compReq);
        model.addAttribute("approve", new Approve());

        return "comp-req-detail";
    }

    // After Manager clicks "approve" or "reject" Compensation
    @PostMapping("/claim/edit/{comp_id}")
    public String ApproveOrRejectCompensation(Approve approve, @PathVariable long comp_id){

        CompensationRequest compReq = compRequestService.findCompensationRequest(comp_id);
        String decision = approve.getDecision();

        compRequestService.updateCompReqAndLeaveBalance(compReq, decision);

        return "redirect:/manager/pending";
    }
}
