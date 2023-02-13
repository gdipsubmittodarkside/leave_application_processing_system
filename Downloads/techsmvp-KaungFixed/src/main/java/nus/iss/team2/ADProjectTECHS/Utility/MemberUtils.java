package nus.iss.team2.ADProjectTECHS.Utility;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MemberUtils {


    public static String getMemberFromSpringSecurity(){
        String currentUsername;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsername = authentication.getName();
        } else {
            currentUsername = null;
        }
        return currentUsername;
    }


}

