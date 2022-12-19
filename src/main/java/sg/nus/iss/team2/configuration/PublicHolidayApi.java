package sg.nus.iss.team2.configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sg.nus.iss.team2.model.PublicHoliday;

@Component
public class PublicHolidayApi {
    private static String PUBLIC_API_URL;

    public List<PublicHoliday> getPublicHoliday(String year)
            throws IOException, InterruptedException {
        PUBLIC_API_URL = "https://date.nager.at/api/v3/publicholidays/" + year + "/SG";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(PUBLIC_API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();
        List<PublicHoliday> holidays = mapper.readValue(response.body(),
                new TypeReference<List<PublicHoliday>>() {

                });
        return holidays;

    }
}
