package sg.nus.iss.team2.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.model.Role;

@Component
public class RoleValidator  implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz)  {
        return Role.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate (Object target, Errors errors) {
        Role role = (Role) target;
        
        String rolename = role.getRoleName();

        if(!rolename.isEmpty() && 
        !rolename.equalsIgnoreCase("admin") &&
        !rolename.equalsIgnoreCase("staff") &&
        !rolename.equalsIgnoreCase("manager")) {
            errors.rejectValue("roleName", "error.roleName","RoleName must be either admin/staff/manager");
        }
       }
}
