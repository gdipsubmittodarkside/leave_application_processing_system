package sg.nus.iss.team2.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.User;

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

    @GetMapping(value = "/viewLeave")
    public String viewLeave(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("userSession");
        model.addAttribute("user", user);
        List<Leave> leaves = user.getLeaves();
        model.addAttribute("leaves", leaves);
        LeaveBalance balance = user.getLeaveBalance();
        model.addAttribute("balance", balance);
        return "viewLeave";
 
    }

    @GetMapping(value = "/claimLeave")
    public String claimLeave(Model model){
        CompensationRequest cr = new CompensationRequest();
        model.addAttribute("compensation", cr);

        return "claimLeave";
    }

    @PostMapping(value = "/claimLeave")
    public String claimCompensation(Model model){


        return "redirect:/viewLeave";
    }


    



}
