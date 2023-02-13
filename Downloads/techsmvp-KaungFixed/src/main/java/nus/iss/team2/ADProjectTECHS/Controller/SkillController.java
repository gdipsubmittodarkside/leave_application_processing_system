package nus.iss.team2.ADProjectTECHS.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Repository.JobSkillRepository;
import nus.iss.team2.ADProjectTECHS.Service.CourseCrawledService;
import nus.iss.team2.ADProjectTECHS.Service.JobService;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;

@Controller
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillService skillService;


    @Autowired
    private CourseCrawledService courseCrawledService;
    @Autowired
    private JobSkillRepository jobSkillRepository;


    // @GetMapping("/searchBySkillTitle")
    // public String FindCoursesBySkillTitle(@RequestParam String skillTitleEntered, Model model){
       
    //     List<CourseCrawled> courses = courseCrawledService.findCoursesBySkillTitleLike(skillTitleEntered);

    //     model.addAttribute("entered", skillTitleEntered);
    //     model.addAttribute("courseList", courses);
    //     currentList = courses;
    //     return findPaginated(1, 5, model);
    // }

    //--search skill by job---
    //job id == skill id 
    //skillRespository

//    @GetMapping("/searchByJobTitle")
//    public String findSkillsByJobTitle(@RequestParam String jobTitleEntered, Model model){
//
//        jobTitleEntered = jobTitleEntered.toLowerCase();
//        List<Job> jobs = jobService.findJobByJobTitleLike(jobTitleEntered);
//
//
//        List<JobSkill> jobSkills = (List<JobSkill>) jobs.stream().map(job -> jobSkillRepository.findSkillsByJobId(job.getJobId()));
//
//
//        List<Skill> skillList = new ArrayList<>();
//
//        for(JobSkill js : jobSkills){
//
//            skillList.add(js.getSkill());
//
//        }
//
//        model.addAttribute("skillList", skillList);
//
//        return "Feature1-SearchSkills/skill-result";
//
//    }



//    @PostMapping("/searchIntro/{id}")
//    public String showIntroduction(@ModelAttribute Skill skill, @PathVariable Long id, Model model){
//
//
//        Skill targetSkill = skillService.findSkillById(id);
//
//
//        String separator = "=";
//        String url = targetSkill.getUrlLink();
//        int sepPos = url.indexOf(separator);
//        String urlQuery = url.substring(sepPos+separator.length());
//
//        model.addAttribute("urlQuery", urlQuery);
//        model.addAttribute("targetSkill", targetSkill);
//        return "Feature1-SearchSkills/skill-result";
//    }

    @PostMapping("/searchIntro/{skillId}")
    @ResponseBody
    public List<String> showIntroduction(@ModelAttribute Skill skill, @PathVariable Long skillId, Model model){


        Skill targetSkill = skillService.findSkillById(skillId);
        List<String> selectedSkill = new ArrayList<>();

        String separator = "=";
        String url = targetSkill.getUrlLink();
        int sepPos = url.indexOf(separator);
        String urlQuery = url.substring(sepPos+separator.length());

        String embedded = "https://www.youtube.com/embed/";
        String finalURL = embedded+urlQuery;

        selectedSkill.add(targetSkill.getSkillTitle());
        selectedSkill.add(targetSkill.getSkillDescription());
        selectedSkill.add(finalURL);


        return selectedSkill;
    }


    @GetMapping("/searchIntro/{skillId}")
    public String showCoursesOfOneSkill(@PathVariable("skillId")Long skillId, Model model) {

        Skill skill = skillService.findSkillById(skillId);

        if (skill == null) throw new RuntimeException("cannot find skill by skill Id");


        List<CourseCrawled> courseCrawledList = courseCrawledService.findCoursesBySkill(skill);

        model.addAttribute("courseList", courseCrawledList);

        return "Feature2-SearchCourse/course-result";

    }




    


    
}
