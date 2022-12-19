package sg.nus.iss.team2.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.model.Employee;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports (Class<?> clazz){
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.employeeName", "Employee name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managerId", "error.managerId", "Manager ID is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "error.User", "User reference is required");
        
        Employee emp = (Employee) target;

        // status must be existing or resigned
        if (!emp.getStatus().equalsIgnoreCase("resigned") &&
            !emp.getStatus().equalsIgnoreCase("existing")){
                errors.rejectValue("status", "error.Employee.status", "Employee status should be \'existing\' or \'resigned\'");
            }

        // name must be between 3 to 30 characters
        if (emp.getName().length() < 3 || emp.getName().length() > 30){
            errors.rejectValue("name", "error.Employee.name", "Employee name should be between 3 to 30 characters");
        }

        // name should not have special characters or digits
        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher m = p.matcher(emp.getName());
        if (m.find()){
            errors.rejectValue("name", "error.Employee.name", "Employee name should not contain any digits or special characters");
        }
    }
}
