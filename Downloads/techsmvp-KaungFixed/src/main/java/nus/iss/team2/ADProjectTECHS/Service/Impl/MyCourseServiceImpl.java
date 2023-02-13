package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Repository.MyCourseRepository;
import nus.iss.team2.ADProjectTECHS.Service.MyCourseService;


@Service
public class MyCourseServiceImpl implements MyCourseService{
    @Autowired 
    private MyCourseRepository mcRepo;

    

    @Override
    public MyCourse createMyCourse(MyCourse myCourse) {
        
        return mcRepo.saveAndFlush(myCourse);
    }

    @Override
    public MyCourse updateMyCourse(MyCourse myCourse, Long id) {
        MyCourse course = mcRepo.findById(id).get();
        course.setMyCourseTitle(myCourse.getMyCourseTitle());
        course.setProgress(myCourse.getProgress());
        course.setScheduleEvent(myCourse.getScheduleEvent());
        course.setSkill(myCourse.getSkill());
        
        return mcRepo.saveAndFlush(course);
    }

    @Override
    public Boolean deleteMyCourse(Long id) {
        MyCourse course = mcRepo.findById(id).get();
        if(course != null){
            mcRepo.delete(course);
            return true;
        }
        return false;
    }

    @Override
    public List<MyCourse> getAllMyCourses() {
        // TODO Auto-generated method stub
        return mcRepo.findAll();
    }

    @Override
    public MyCourse findMyCourseById(Long id) {
        // TODO Auto-generated method stub
        return mcRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<MyCourse> getMyCoursesByMemberId(Long id){
        return mcRepo.findMyCoursesByMemberId(id);
    }

    @Override
    public MyCourse findMyCourseByTitle(String title, Long memberId) {
        return mcRepo.findMyCoursesByTitle(title, memberId).orElse(null);
    }

}
