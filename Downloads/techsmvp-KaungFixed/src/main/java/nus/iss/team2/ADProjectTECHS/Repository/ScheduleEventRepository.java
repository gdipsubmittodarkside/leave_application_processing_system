package nus.iss.team2.ADProjectTECHS.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;

@Repository
public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long>{
    @Override
    Optional<ScheduleEvent> findById(Long Id);


    List<ScheduleEvent> findScheduleEventByMember(Member member);

    @Query("select s from ScheduleEvent s where s.member.memberId = :memberId")
    List<ScheduleEvent> findScheduleEventByMemberId(@Param("memberId") Long memberId);

    @Query("select s from ScheduleEvent s where s.startDate = :sd and s.endDate = :ed")
    ScheduleEvent findScheduleEventByStartDateAndEndDate(@Param("sd") LocalDate sd, @Param("ed") LocalDate ed);

    Optional<ScheduleEvent> findScheduleEventByMemberAndMyCourse(Member member, MyCourse myCourse);


    void deleteByScheduleId(Long id);
}

