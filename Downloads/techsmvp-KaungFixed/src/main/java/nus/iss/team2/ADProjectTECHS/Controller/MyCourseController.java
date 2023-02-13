package nus.iss.team2.ADProjectTECHS.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.MySkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Service.CourseCrawledService;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;
import nus.iss.team2.ADProjectTECHS.Service.MyCourseService;
import nus.iss.team2.ADProjectTECHS.Service.MySkillService;
import nus.iss.team2.ADProjectTECHS.Service.PythonAPIService;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;
import nus.iss.team2.ADProjectTECHS.Utility.MemberUtils;

@Controller
@RequestMapping("/myCourses")
public class MyCourseController {

    @Autowired
    private MyCourseService myCourseService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PythonAPIService pythonAPIService;

    @Autowired
    private CourseCrawledService courseCrawledService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private MySkillService mySkillService;
    
    // CREATE
    // Saving of new courses done at "CourseController"

    // READ
    @GetMapping("")
    public String ViewMyCourses(Model model){

       
        //find member
        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if (currentMember == null) throw new RuntimeException("cannot find current member");

        

        List<MyCourse> myCourseList = myCourseService.getMyCoursesByMemberId(currentMember.getMemberId());

        List<String> skillTitles = new ArrayList<>();
       


        for (MyCourse mc : myCourseList){

            skillTitles.add((skillService.findSkillById(mc.getSkill())).getSkillTitle());

        }

		model.addAttribute("skillTitles",skillTitles);


        // List<MyCourse> myCourseList = myCourseService.getAllMyCourses();
        model.addAttribute("myCourseList", myCourseList);
        return "Feature3-Dashboard/myCourses";
    }

    // DELETE


          // READ
    @GetMapping("/manageProgress")
    public String ManageProgress(Model model){
      
    
        // Member currentMember = memberService.findById(userId);  
        
        //find member
        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if (currentMember == null) throw new RuntimeException("cannot find current member");

      
        //get MyCourse
        List<MyCourse> myCourseList = myCourseService.getMyCoursesByMemberId(currentMember.getMemberId());
              
      
        //get progress
        List<Integer> progressList = new ArrayList<>();
        for(MyCourse mc : myCourseList){
                progressList.add(mc.getProgress());
        }
      
        //inprogess courses
        List<MyCourse> inProgressList = new ArrayList<>();
      
        //completed courses if progress 100%
        List<MyCourse> completedList = new ArrayList<>();
          
          
        //add in 2 lists with condition
        for(MyCourse c: myCourseList){
            if(c.getProgress()<100){
                     inProgressList.add(c);
                }
            else{
                      completedList.add(c);
                }
        }
      
      
              model.addAttribute("myCourseList", myCourseList);
              model.addAttribute("inProgressList",inProgressList);
              model.addAttribute("completedLlist",completedList);
      
              return "Feature3-Dashboard/course";
          }


    @GetMapping(value={"/watchCourse/{id}"})
    public String WatchCourseVideo(@PathVariable Long id, Model model){

        //find current selected course
        MyCourse course = myCourseService.findMyCourseById(id);
        if(course==null) throw new RuntimeException("cannot find course in my course");

        //substring url for embedded purpose
        String separator = "=";
        String url = course.getCourseUrl();


        int sepPos = url.indexOf(separator);
        String urlQuery = url.substring(sepPos+separator.length());


        //find channel_name, descrioption and date from courseCrawled table
        CourseCrawled currentCourse = courseCrawledService.findCourseCrawledByUrl(url);
        //if(currentCourse == null) throw new RuntimeException("cannot find current course");

        //model.addAttribute("Mycourse", course);
        model.addAttribute("urlQuery",urlQuery);
        model.addAttribute("course", currentCourse);

        return "Feature2-SearchCourse/watchCourse";
    }
      
          
      
    @GetMapping("/updateProgress/{id}")
    public String updateProgress(@PathVariable Long id, @RequestParam("updatedProgress") String progress, Model model){

        //kaung code
        String currentUsername = MemberUtils.getMemberFromSpringSecurity();
        Member currentMember = memberService.loadMemberByUsername(currentUsername);
                
        int  updatedProgress= Integer.parseInt(progress);
        
        MyCourse existingCourse = myCourseService.findMyCourseById(id);

        existingCourse.setProgress(updatedProgress);
    
        myCourseService.updateMyCourse(existingCourse, id);

        //kaung
        if(updatedProgress == 100){
            MySkill mySkill = new MySkill();
            Long skillId = existingCourse.getSkill();
            Skill newSkill = skillService.findSkillById(skillId);
            Boolean isOwned = false;
            for (MySkill ms : currentMember.getMySkills()) {
                if(ms.getSkill() == newSkill){
                    isOwned = true;
                    break;
                }
            }
            if(!isOwned){
                mySkill.setCourseTaken(existingCourse.getMyCourseTitle());
                mySkill.setSkill(newSkill);
                mySkill.setMember(currentMember);
                mySkillService.save(mySkill);
            }

        }
        return "redirect:/myCourses/manageProgress";
    }



    

}
