package sg.nus.iss.team2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping (value = {"/","/home","/login"})
    public String Login(){
        return "login";
    }


}
