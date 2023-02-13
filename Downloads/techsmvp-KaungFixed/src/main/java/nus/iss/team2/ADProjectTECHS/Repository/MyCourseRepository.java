package nus.iss.team2.ADProjectTECHS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.MyCourse;


@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse, Long>{

    @Query("select mc from MyCourse mc where mc.member.memberId= :memberId")
    List<MyCourse> findMyCoursesByMemberId(@Param("memberId")Long memberId);

    @Query("select mc from MyCourse mc where mc.myCourseTitle = :title and mc.member.memberId = :memberId ")
    Optional<MyCourse> findMyCoursesByTitle(@Param("title") String title,@Param("memberId")Long memberId);
    
}
