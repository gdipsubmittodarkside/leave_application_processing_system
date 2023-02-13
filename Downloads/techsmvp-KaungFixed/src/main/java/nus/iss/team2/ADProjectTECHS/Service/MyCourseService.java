package nus.iss.team2.ADProjectTECHS.Service;
import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.MyCourse;


public interface MyCourseService {
    MyCourse findMyCourseById(Long id);
    MyCourse createMyCourse(MyCourse myCourse);
    MyCourse updateMyCourse(MyCourse myCourse, Long id);
    Boolean deleteMyCourse(Long id);
    List<MyCourse> getAllMyCourses();

    List<MyCourse> getMyCoursesByMemberId(Long id);

    MyCourse findMyCourseByTitle(String title,Long memberId);

}
