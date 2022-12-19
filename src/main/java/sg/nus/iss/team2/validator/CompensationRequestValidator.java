package sg.nus.iss.team2.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team2.model.CompensationRequest;

@Component
public class CompensationRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CompensationRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "OTstartTime", "OTstartTime","OT start Time is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "OTendTime", "OTendTime", "OT end Time is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description", "Description is required");

        CompensationRequest cr = (CompensationRequest) target;

        LocalDateTime OTstartTime = cr.getOTstartTime();
        LocalDateTime OTendTime = cr.getOTendTime();

        if(OTstartTime!=null && OTendTime!=null &&  OTstartTime.compareTo(OTendTime) > 0){

            errors.rejectValue("OTstartTime","error.OTstartTime","OT start Time cannot be later than OT end Time.");
        }
        
    }
}