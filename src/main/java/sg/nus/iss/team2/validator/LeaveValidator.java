package sg.nus.iss.team2.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.Utility.Calculate;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;


@Component
public class LeaveValidator implements Validator {

    @Autowired
    Calculate calculator;
    
    @Override
    public boolean supports(Class<?> clazz){
        return Leave.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate (Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "error.leaveReason","A reason is required");

        Leave leave = (Leave) target;
        
        // if start date is after (greater than) end date
        if ((leave.getStartDate() != null && leave.getEndDate() != null) &&
        (leave.getStartDate().compareTo(leave.getEndDate()) > 0)){
            errors.reject("endDate", "Leave end date should be greater than start date.");
        }

        // if leave duration is more than existing leave balance
        double leaveDuration = calculator.numOfDaysMinusPHAndWeekend(leave.getStartDate(), leave.getEndDate());
        double maxLeaveDays = 0;
        String leaveType = leave.getLeaveType().toString();

        Employee emp = leave.getEmployee();
        LeaveBalance leaveBalance = emp.getLeaveBalance();

        if (leaveType.equalsIgnoreCase("annual")){
            maxLeaveDays = leaveBalance.getBalanceAnnualLeaveDays();
        }
        else if (leaveType.equalsIgnoreCase("medical")){
            maxLeaveDays = leaveBalance.getBalanceMedicalLeaveDays();
        }
        else if (leaveType.equalsIgnoreCase("compensation")){
            maxLeaveDays = leaveBalance.getBalanceCompensationLeaveDays();
        }

        if (leaveDuration !=0 && (leaveDuration > maxLeaveDays)){
            errors.rejectValue("endDate", "error.Leave.endDate", String.format("Invalid end date. Max {0} leave days available is {1}.", leaveType.toLowerCase(), maxLeaveDays));
        }

    }
}
