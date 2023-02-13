package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.time.LocalDate;
import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;
import nus.iss.team2.ADProjectTECHS.Repository.ScheduleEventRepository;
import nus.iss.team2.ADProjectTECHS.Service.ScheduleEventService;

import javax.transaction.Transactional;


@Service
@Transactional
public class ScheduleEventServiceImpl implements ScheduleEventService{


    @Autowired
    ScheduleEventRepository seRepo; 

    @Override
    public List<ScheduleEvent> findScheduleEventByMemberId(Long memberId){

        return seRepo.findScheduleEventByMemberId(memberId);
    }

    @Override
    public ScheduleEvent findScheduleEventByStartDateAndEndDate(LocalDate sd, LocalDate ed) {
        return seRepo.findScheduleEventByStartDateAndEndDate(sd, ed);
    }

    @Override
    public ScheduleEvent findScheduleEventByMemberAndMyCourse(Member member, MyCourse myCourse) {
        return seRepo.findScheduleEventByMemberAndMyCourse(member, myCourse).orElse(null);
    }

    @Override
    public void delete(ScheduleEvent se) {
        seRepo.delete(se);
    }

    @Override
    public ScheduleEvent findScheduleEventById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ScheduleEvent createScheduleEvent(ScheduleEvent event) {
        // TODO Auto-generated method stub
        return seRepo.save(event);
    }

    @Override
    public ScheduleEvent updateScheduleEvent(ScheduleEvent event) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteScheduleEvent(Long id) {
        // TODO Auto-generated method stub
       try {

           seRepo.deleteByScheduleId(id);
           return true;
       } catch (Exception e){
           return false;
       }
    }

    @Override
    public List<ScheduleEvent> getAllScheduleEvents() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
