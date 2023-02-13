package nus.iss.team2.ADProjectTECHS.Config;

import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Service.CourseService;


@Component
@AllArgsConstructor
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-mm-dd");

    private CourseService courseService;


    @Scheduled(cron = "0 0 1 * * MON")
    public void getCoursesFromPython() {
        courseService.getAndSaveCrawledCourses();

    }
}
