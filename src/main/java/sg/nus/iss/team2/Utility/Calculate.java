package sg.nus.iss.team2.Utility;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import sg.nus.iss.team2.model.PublicHoliday;
import sg.nus.iss.team2.service.PublicHolidayService;

@Component("Calculate")
public class Calculate {
    
    @Autowired
    PublicHolidayService phService;
    
    public long durationInHours(LocalDateTime startTime, LocalDateTime endTime){
        return ChronoUnit.HOURS.between(startTime, endTime);
    }
    // Tested method "numOfDaysMinusPHAndWeekend"
        /*
        LocalDateTime start = LocalDateTime.of(2022, Month.DECEMBER, 29, 19, 00, 00);
        LocalDateTime end = LocalDateTime.of(2023, Month.JANUARY, 3, 19, 00 ,00);
        double days = calculator.numOfDaysMinusPHAndWeekend(start, end);
            
        System.out.println(days); // prints 3.0 (correct)
         */
    public double numOfDaysMinusPHAndWeekend(LocalDate startDate, LocalDate endDate)
    {
        // int numOfDays = Period.between(startDate, endDate).getDays() + 1;
        int numOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate)+1;
            // 6 (period.between does not include last day)

       List<PublicHoliday> publicHolidays = phService.findAllPublicHolidays();


        // the total list of PH dates for both years
        List<String> totalPHs = publicHolidays.stream().map(ph->ph.getDate().toString()).toList();

        // date string format: "2019-02-18"
        List<LocalDate> formattedTotalPH = totalPHs.stream().map(d -> LocalDate.parse(d)).collect(Collectors.toList());

        // compare
        List<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
            // need to endDate.plusDays(1) because datesUntil exclude last day

        for (LocalDate date : dates)
        {
            // minus PH
            if (formattedTotalPH.contains(date)){
                numOfDays--;
            }

            // minus weekends, if date is not PH
            if ((date.getDayOfWeek().toString().equals("SUNDAY") || date.getDayOfWeek().toString().equals("SATURDAY"))
                && !formattedTotalPH.contains(date)
            ){
                numOfDays--;
            }
        }
        
        return numOfDays;
    }

}
