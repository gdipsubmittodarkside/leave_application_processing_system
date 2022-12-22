// yt updated 19th Dec 9pm

package sg.nus.iss.team2.controller;

import java.io.IOException;
import java.time.LocalDate;
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

import sg.nus.iss.team2.model.*;
import sg.nus.iss.team2.reporting.FilesExporter;
import sg.nus.iss.team2.service.*;
import sg.nus.iss.team2.validator.ApproveValidator;

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
    private ApproveValidator approveValidator;

    @Autowired
    private FilesExporter export;

    @InitBinder("approve")
    private void initCourseBinder(WebDataBinder binder) {
        binder.addValidators(approveValidator);
    }

    // Get the List of Employees under the Manager
    public List<Employee> FindEmployeesUnderManager() {
        User manager = (User) session.getAttribute("userSession");
        Long managerId = manager.getUserId();

        return empService.findSubordinates(managerId);
    }

    // View Employees' Leave History
    @GetMapping("/team-leave")
    public String ViewTeamLeaveHistory(Model model) {
        List<Employee> team = FindEmployeesUnderManager();

        List<Leave> teamLeaves = leaveService.findTeamLeaveHistory(team);
        List<CompensationRequest> compHistory = compRequestService.findTeamCompensationHistory(team);

        model.addAttribute("compHistory", compHistory);
        model.addAttribute("teamLeaves", teamLeaves);

        return "team-leave";
    }

    // View Pending Approvals (1) leave, (2) claims
    @GetMapping("/pending")
    public String ViewPendingApprovals(Model model) {
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
    // Show leave records of his/her subordinates during the leave period are listed
    // Logic:
    // 1. From the leaveid --> look for the leave period
    // 2. From the managerid --> look for the subordinates id
    // 3. Get his/her subordinates leave record that coincide with the leave period
    // 4. Display them

    @PostMapping("/leave/display/{id}")
    public String ViewLeaveDetails(@PathVariable Long id, Model model) {
        Leave leave = leaveService.findLeave(id);

        LocalDate theLeaveStartDate = leave.getStartDate();
        LocalDate theLeaveEndDate = leave.getEndDate();

        List<Employee> team = FindEmployeesUnderManager();

        List<Leave> teamLeaves = leaveService.findApprovedTeamLeaveHistory(team, theLeaveStartDate, theLeaveEndDate);// only:
                                                                                                                     // approved,
                                                                                                                     // and
                                                                                                                     // the
                                                                                                                     // same
                                                                                                                     // period

        model.addAttribute("teamLeaves", teamLeaves);
        model.addAttribute("leave", leave);
        model.addAttribute("approve", new Approve());

        return "manager-leave-detail";
    }

    @GetMapping("/exportLeaveHiscsv")
    public void exportLeaveHistCSV(HttpServletResponse response) throws IOException {
        List<Employee> team = FindEmployeesUnderManager();
        List<Leave> leaveHis = leaveService.findTeamLeaveHistory(team);
        export.exportLeaveHisToCSV(leaveHis, response);
    }

    @GetMapping("/exportCompHistcsv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Employee> team = FindEmployeesUnderManager();
        List<CompensationRequest> comHistory = compRequestService.findTeamCompensationHistory(team);
        export.exportCompensationHisToCSV(comHistory, response);
    }

    // After Manager clicks "approve" or "reject" Leave
    @PostMapping("/leave/edit/{leave_id}")
    public String ApproveOrRejectLeave(@Valid @ModelAttribute Approve approve, BindingResult bindingResult,
            @PathVariable long leave_id, Model model) {

        // To rewrite the data so that when it returns, we can get them back.
        if (bindingResult.hasErrors()) {
            Leave leave = leaveService.findLeave(leave_id);
            LocalDate theLeaveStartDate = leave.getStartDate();
            LocalDate theLeaveEndDate = leave.getEndDate();

            List<Employee> team = FindEmployeesUnderManager();
            List<Leave> teamLeaves = leaveService.findApprovedTeamLeaveHistory(team, theLeaveStartDate,
                    theLeaveEndDate);// only: approved, and the same period

            model.addAttribute("teamLeaves", teamLeaves);
            model.addAttribute("leave", leave);

            return "manager-leave-detail";

        }

        Leave leave = leaveService.findLeave(leave_id);
        String decision = approve.getDecision();
        String comment = approve.getComment();

        leaveService.updateLeaveAndLeaveBalance(leave, decision, comment);

        return "redirect:/manager/pending";

    }

    // when "Detail" button is clicked, display details of 1 particular Claim
    // Request
    // will show approve/reject option
    @PostMapping("/claim/display/{comp_id}")
    public String ViewCompensationRequest(@PathVariable Long comp_id, Model model) {
        CompensationRequest compReq = compRequestService.findCompensationRequest(comp_id);

        model.addAttribute("compRequest", compReq);
        model.addAttribute("approve", new Approve());

        return "comp-req-detail";
    }

    // After Manager clicks "approve" or "reject" Compensation
    @PostMapping("/claim/edit/{comp_id}")
    public String ApproveOrRejectCompensation(@Valid @ModelAttribute Approve approve, BindingResult bindingResult,
            @PathVariable long comp_id, Model model) {

        if (bindingResult.hasErrors()) {
            CompensationRequest compReq = compRequestService.findCompensationRequest(comp_id);
            model.addAttribute("compRequest", compReq);
            return "comp-req-detail";

        }

        CompensationRequest compReq = compRequestService.findCompensationRequest(comp_id);
        String decision = approve.getDecision();
        String comment = approve.getComment();

        compRequestService.updateCompReqAndLeaveBalance(compReq, decision, comment);

        return "redirect:/manager/pending";
    }
}
