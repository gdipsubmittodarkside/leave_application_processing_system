package nus.iss.team2.ADProjectTECHS.Service;

import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;

public interface CourseService {

    List<CourseCrawled> getAndSaveCrawledCourses();
}
