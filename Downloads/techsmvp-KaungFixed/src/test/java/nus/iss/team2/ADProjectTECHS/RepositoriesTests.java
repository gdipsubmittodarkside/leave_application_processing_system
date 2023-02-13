package nus.iss.team2.ADProjectTECHS;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.MySkill;
import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Repository.CourseCrawledRepository;
import nus.iss.team2.ADProjectTECHS.Repository.JobRepository;
import nus.iss.team2.ADProjectTECHS.Repository.JobSkillRepository;
import nus.iss.team2.ADProjectTECHS.Repository.MemberRepository;
import nus.iss.team2.ADProjectTECHS.Repository.MyCourseRepository;
import nus.iss.team2.ADProjectTECHS.Repository.MySkillRepository;
import nus.iss.team2.ADProjectTECHS.Repository.ScheduleEventRepository;
import nus.iss.team2.ADProjectTECHS.Repository.SkillRepository;

@DataJpaTest //have one embedded h2 database
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE) // do not use embedded database
public class RepositoriesTests {

    @Autowired
    private CourseCrawledRepository courseCrawledRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MyCourseRepository myCourseRepository;
    @Autowired
    private MySkillRepository mySkillRepository;
    @Autowired
    private ScheduleEventRepository scheduleEventRepository;
    @Autowired
    private SkillRepository skillRepository;


    @Test
    public void saveMemberTest() {

        Member member = new Member();
        member.setUsername("8L");

        memberRepository.save(member);


        Member memberAfter = memberRepository.findMemberByUsername("8L").orElse(null);
        Assertions.assertNotEquals(memberAfter, null);

    }

    @Test
    public void getMemberTest() {
        Optional member = memberRepository.findById(1L);
        Assertions.assertEquals(member.isPresent(),true);
    }

    @Test
    public void getListOfMemberTest() {

        List<Member> memberList = memberRepository.findAll();
        Assertions.assertEquals(memberList.size() > 5, true);
    }

    @Test
    public void updateMemberTest() {

        Member member = memberRepository.findById(2L).orElse(null);
        Job job = jobRepository.findById(4L).orElse(null);
        member.setCurrentCity("Roma");
        member.setDreamJob(job);
        Member member1 =  memberRepository.findMemberByCurrentCity("Roma").orElse(null);
        Assertions.assertNotEquals(member1, null);


    }

    @Test
    public void deleteMemberTest() {
        memberRepository.deleteById(7l);
        Member member = memberRepository.findById(7L).orElse(null);
        Assertions.assertEquals(member, null);

    }


    @Test
    public void saveScheduleEvent() {

        ScheduleEvent scheduleEvent = new ScheduleEvent();
        Member member = memberRepository.findById(4L).orElse(null);
        //Skill skill = skillRepository.findById(1L).orElse(null);
        MyCourse myCourse = myCourseRepository.findById(1L).orElse(null);

        scheduleEvent.setMember(member);
        scheduleEvent.setMyCourse(myCourse);

        scheduleEventRepository.save(scheduleEvent);

        List<ScheduleEvent> scheduleEventList = scheduleEventRepository.findScheduleEventByMember(member);
        Assertions.assertEquals(scheduleEventList.contains(scheduleEvent), true);


    }

    @Test
    public void updateMySkillTest() {

        MySkill mySkill = mySkillRepository.findById(2L).orElse(null);
        Member member = memberRepository.findById(3L).orElse(null);
        mySkill.setCourseTaken("no course");
        mySkill.setMember(member);
        mySkillRepository.save(mySkill);

        List<MySkill> updateSkills = mySkillRepository.findMySkillsByMember(member);

        Assertions.assertEquals(updateSkills.contains(mySkill),true);



    }


    @Test
    public void saveJobAndSkillTest() {
        JobSkill jobSkill = new JobSkill();
        Skill skill = skillRepository.findById(1l).orElse(null);
        Job job = jobRepository.findById(2l).orElse(null);
        jobSkill.setLastUpdatedDate(LocalDate.of(2023,1,1));
        jobSkill.setSkill(skill);
        jobSkill.setJob(job);
        jobSkillRepository.save(jobSkill);
        List<JobSkill> savedJobSkills = jobSkillRepository.findJobSkillByJobAndAndSkill(job, skill);

        Assertions.assertEquals(savedJobSkills.contains(jobSkill),true);
    }


    @Test
    public void updateCourseCrawledRepository() {

        CourseCrawled courseCrawled = courseCrawledRepository.findById(10L).orElse(null);
        courseCrawled.setLikes(100);
        courseCrawledRepository.save(courseCrawled);

        CourseCrawled updatedCourseCrawled = courseCrawledRepository.findById(10l).orElse(null);

        Assertions.assertEquals(updatedCourseCrawled.getLikes(),100);

    }



}

