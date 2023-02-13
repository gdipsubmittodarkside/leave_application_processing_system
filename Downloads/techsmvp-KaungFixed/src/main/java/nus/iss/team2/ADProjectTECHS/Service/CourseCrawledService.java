package nus.iss.team2.ADProjectTECHS.Service;
import java.util.List;
import java.util.Optional;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import org.springframework.data.domain.Page;


public interface CourseCrawledService {
    List<CourseCrawled> findCoursesSortedBySubscribers();

    List<CourseCrawled> findCoursesTitleLike(String title);

    List<CourseCrawled> findCoursesBySkill(Skill skill);

    List<CourseCrawled> findCoursesBySkillTitleLike(String skillTitle);

    List<CourseCrawled> findCoursesSortedByViews();

    void cancelForeignKeyConstraint();

    void enableForeignKeyConstraint();

    void truncateTable();

    List<CourseCrawled> getCourseCrawledList();

    CourseCrawled saveCourseCrawled(CourseCrawled courseCrawled);

    Optional<CourseCrawled> findCourseCrawled(long id);

    CourseCrawled findCourseCrawledById(long id);

    CourseCrawled updateCourseCrawled(CourseCrawled courseCrawled);

    Boolean deleteCourseCrawledById(Long id);

    Page<CourseCrawled> findPaginated(int pageNo, int pageSize);

    List<CourseCrawled> findCoursesBySkillId(long skillId);

    List<CourseCrawled> recommend_best_match(String query, List<MyCourse> myCourses);

    CourseCrawled findCourseCrawledByUrl(String url);

}
