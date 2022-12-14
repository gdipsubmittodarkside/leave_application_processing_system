package sg.nus.iss.team2.Utility;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class Calculate {
    
    public long durationInHours(LocalDateTime startTime, LocalDateTime endTime){
        return ChronoUnit.HOURS.between(startTime, endTime);
    }
}
