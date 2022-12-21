package sg.nus.iss.team2.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.model.LeaveType;

@Component
public class LeaveTypeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LeaveType.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target);
        System.out.println("In side the Validation");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveTypeName", "error.leavetype.leaveTypeName.empty",
                "Name of the Leave Type cannot be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leavePeriod", "error.leavetype.leavePeriod.empty",
                "Leave Period cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.leavetype.description.empty",
                "Description cannot be empty");
        LeaveType lt = (LeaveType) target;
        if (lt.getLeavePeriod() <= 0) {
            errors.rejectValue("leavePeriod", "error.leavePeriod", "Leave period must be more than 0");
        }
    }
}
