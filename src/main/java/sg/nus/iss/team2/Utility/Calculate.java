package sg.nus.iss.team2.Utility;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.util.Duration;

@Component("Calculate")
public class Calculate {
    
    public long durationInHours(LocalDateTime startTime, LocalDateTime endTime){
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    public List<String> GetSGPublicHolsByYear(Integer year) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://public-holiday.p.rapidapi.com/" + year.toString() + "/SG"))
		.header("X-RapidAPI-Key", "33cbf0e762msh3a277500450f53fp1dcdacjsncd18182b0dbc")
		.header("X-RapidAPI-Host", "public-holiday.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        String responseString = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        List<SGPublicHolidays> PHlist = Arrays.asList(
            objectMapper.readValue(responseString, SGPublicHolidays[].class));
        
        // System.out.println(ph2022.size()); // 11
        
        List<String> phDates = new ArrayList<String>();
        
        for (SGPublicHolidays ph : PHlist){
            phDates.add(ph.getDate());
        }
        
        // System.out.println(phDates.size()); // 11 (correct)
        return phDates;
        
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

        Integer startYear = startDate.getYear();
        Integer endYear = endDate.getYear();

        List<String> phStartYear = new ArrayList<String>();
        List<String> phEndYear = new ArrayList<String>();

        // Method "GetSGPublicHolsByYear(year)" throws exception. to solve later.
        try
        {
            
            phStartYear = GetSGPublicHolsByYear(startYear);
            phEndYear = GetSGPublicHolsByYear(endYear);
            
        }
        catch (Exception e)
        {
            e.getMessage();
        }

        // the total list of PH dates for both years
        List<String> totalPHs = new ArrayList<String>();

        if (!startYear.equals(endYear)){
            totalPHs.addAll(phStartYear);
            totalPHs.addAll(phEndYear);
        }
        else{
            totalPHs.addAll(phStartYear);
        }

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
