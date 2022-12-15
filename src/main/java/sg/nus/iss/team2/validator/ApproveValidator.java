package sg.nus.iss.team2.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sg.nus.iss.team2.model.Approve;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveStatusEnum;

@Component
public class ApproveValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Approve.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        Approve approve = (Approve)object;
        
        String decision = approve.getDecision();

        if (decision != null)
        {
            if (decision.equalsIgnoreCase(LeaveStatusEnum.REJECTED.toString()))
            {
                String comment = approve.getComment();
                if (comment.isEmpty())
                {
                    errors.rejectValue("comment","error.comment","Please provide a reason");
                }
                
            }

        }


    }
}
    

