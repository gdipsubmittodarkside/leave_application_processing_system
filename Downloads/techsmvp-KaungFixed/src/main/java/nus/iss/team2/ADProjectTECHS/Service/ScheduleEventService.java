package nus.iss.team2.ADProjectTECHS.Service;

import java.time.LocalDate;
import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;


public interface ScheduleEventService {
    ScheduleEvent findScheduleEventById(Long id);
    ScheduleEvent createScheduleEvent(ScheduleEvent event);
    ScheduleEvent updateScheduleEvent(ScheduleEvent event);
    Boolean deleteScheduleEvent(Long id);
    List<ScheduleEvent> getAllScheduleEvents();

    List<ScheduleEvent> findScheduleEventByMemberId(Long memberId);

    ScheduleEvent findScheduleEventByStartDateAndEndDate(LocalDate sd, LocalDate ed);

    ScheduleEvent findScheduleEventByMemberAndMyCourse(Member member, MyCourse myCourse);

    void delete(ScheduleEvent se);
}
