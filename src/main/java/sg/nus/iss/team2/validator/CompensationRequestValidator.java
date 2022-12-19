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
    public void validate(Object object, Errors errors) {

        CompensationRequest cr = (CompensationRequest)object;
        
        String description = cr.getDescription();

        LocalDateTime starttime = cr.getOTstartTime();
        LocalDateTime endtime = cr.getOTendTime();

        if (starttime==null) 
        {
            errors.rejectValue("OTstartTime","error.OTstartTime","Please select the Start Date & Time");
        }

        if (endtime==null) 
        {
            errors.rejectValue("OTendTime","error.OTendTime","Please select the End Date & Time");
        }
        else
        {
            if (endtime.compareTo(starttime)>0)
            {
                errors.rejectValue("OTendTime", "error.OTendTime", "End Date & Time must be later than Start Date & Time");
    
            }

        }

        if (starttime!=null && endtime!=null)
        {
            if (description == null)
            {
                errors.rejectValue("description","error.description","Please justify your claim");
    
            }

        }






        





        


    
}

}