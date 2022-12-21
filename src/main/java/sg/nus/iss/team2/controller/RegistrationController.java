package sg.nus.iss.team2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.team2.model.Role;
import sg.nus.iss.team2.model.User;

import sg.nus.iss.team2.service.RoleService;
import sg.nus.iss.team2.service.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public ResponseEntity<String> createTutorial(@RequestBody User user) {

        if(!userService.existsUserByUsername(user.getUsername())){
            List<Role> roleList = new ArrayList<>();
            user.getRoleNames().forEach((name) ->{
                Role role = roleService.findRoleByRoleName(name);
                roleList.add(role);
            });
            user.setRoles(roleList);
            userService.createUser(user);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        else {
            throw new RuntimeException();
        }

    }
    

}
