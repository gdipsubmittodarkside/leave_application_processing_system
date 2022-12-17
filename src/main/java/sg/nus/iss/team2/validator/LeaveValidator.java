package sg.nus.iss.team2.validator;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.Utility.Calculate;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.service.LeaveBalanceService;

@Component
public class LeaveValidator implements Validator{

    @Autowired
    Calculate calculate;

    @Autowired
    LeaveBalanceService lbService;
    
    @Override
    public boolean supports (Class<?> clazz){
        return Leave.class.isAssignableFrom(clazz);      
    } 

    @Override
    public void validate (Object target, Errors errors){

        Leave leave = (Leave) target;

        LocalDate startDate = leave.getStartDate();
        LocalDate endDate = leave.getEndDate();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "startDate","Start Date is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "endDate", "End Date is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "reason", "Reason is required");

       

        if (startDate!=null && startDate.compareTo(LocalDate.now()) < 0){

            errors.rejectValue("startDate","error.startDate","Start Date cannot be ealier than today");
        }

        if(startDate!=null && endDate!=null &&  startDate.compareTo(endDate) > 0){

            errors.rejectValue("startDate","error.startDate","StartDate cannot be later than endDate.");
        }

        if (startDate!=null && (startDate.getDayOfWeek().toString().equals("SUNDAY") || startDate.getDayOfWeek().toString().equals("SATURDAY"))){
            errors.rejectValue("startDate","error.startDate2","Please choose working date only!");
        }

        if (startDate!=null && (endDate.getDayOfWeek().toString().equals("SUNDAY") || endDate.getDayOfWeek().toString().equals("SATURDAY"))){
            errors.rejectValue("endDate","error.endDate2","Please choose working date only!");
        }
        
       
    }
}
