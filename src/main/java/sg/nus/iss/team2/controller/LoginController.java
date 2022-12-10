package sg.nus.iss.team2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {

    @GetMapping (value = {"/","/login"})
    public String Login(){
        return "login";
    }


    @RequestMapping("/logout")
    public String Logout(){
        return "login";    
    }


}
