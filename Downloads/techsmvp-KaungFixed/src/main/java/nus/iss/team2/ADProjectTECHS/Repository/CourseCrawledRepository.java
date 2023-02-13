package nus.iss.team2.ADProjectTECHS.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Skill;


@Repository
public interface CourseCrawledRepository extends JpaRepository<CourseCrawled, Long> {
    
    CourseCrawled findCourseCrawledByCourseId(Long id);
    
    @Query("select c from CourseCrawled c order by c.subscribers")
    List<CourseCrawled> findCoursesSortedBySubscribers();

    List<CourseCrawled> findCourseCrawledsByCourseTitleContainsIgnoreCase(String title);


    @Query("select c from CourseCrawled c where c.skill = :skill")
    List<CourseCrawled> findCoursesBySkill(@Param("skill") Skill skill);

    @Query("select c from CourseCrawled c where c.skill.skillTitle like %:skillTitle%")
    List<CourseCrawled> findCoursesCrawledBySkillTitleContains(@Param("skillTitle") String skillTitle);

    @Query("select c from CourseCrawled c order by c.views")
    List<CourseCrawled> findCoursesSortedByViews();


    @Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
    void enableForeignKeyConstraint();

    @Query(value = "SET FOREIGN_KEY_CHECKS = 1", nativeQuery = true)
    void cancelForeignKeyConstraint();

    @Query(value = "truncate table path", nativeQuery = true)
    void truncateTable();

    @Query("select c from CourseCrawled c where c.skill.skillId = :skillId")
    List<CourseCrawled> findCourseBySkillId(@Param("skillId") long skillId);

    @Query(value = "select c from CourseCrawled c where c.urlLink like %:courseId% ")
    List<CourseCrawled> findCourseCrawledByURL(@Param("courseId")String courseId);

    @Query("select c from CourseCrawled c where c.urlLink = :urlLink")
    CourseCrawled findCourseCrawledByUrl(@Param("urlLink")String urlLink);
}
