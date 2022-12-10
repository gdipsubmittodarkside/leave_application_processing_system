package sg.nus.iss.team2.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sg.nus.iss.team2.model.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target);
        ValidationUtils.rejectIfEmpty(errors,"username","error.user.username.empty");
        ValidationUtils.rejectIfEmpty(errors,"password","error.user.password.empty");
    }
}
