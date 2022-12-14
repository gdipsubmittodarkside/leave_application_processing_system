package sg.nus.iss.team2.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.team2.model.User;
// import sg.nus.iss.team2.service.EmployeeService;

import sg.nus.iss.team2.service.UserService;
import sg.nus.iss.team2.validator.UserValidator;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // @Autowired
    // private EmployeeService employeeService;

    @Autowired
    private UserValidator userValidator;
    
    @InitBinder
    private void initUserBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    @GetMapping (value = {"/","/login","/home"})
    public String Login(Model model){
        model.addAttribute("user",new User());
        return "login";
    }


    @PostMapping (value = "/login")
    public String authenticate(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession httpSession){
        if(bindingResult.hasErrors()){
            return "login";
        }
        User userFromDb = userService.login(user.getUsername(),user.getPassword());

        if(userFromDb == null){
            model.addAttribute("loginMessage","Incorrect username/password");
            return "login";
        };


        httpSession.setAttribute("userSession",userFromDb);


        List<String> roleNames = userFromDb.getRoleNames();

        if (roleNames.contains("admin")) {
            return "redirect:/admin/employee/list";
        }

        if (roleNames.contains("manager")) {
            return "redirect:/manager/pending";
        }


        return "redirect:/staff/list";


    }

    @RequestMapping("/logout")
    public String Logout(HttpSession session){
        session.invalidate();
        return "login";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }


}
