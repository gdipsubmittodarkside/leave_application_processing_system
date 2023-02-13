package nus.iss.team2.ADProjectTECHS.Controller;

import lombok.RequiredArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Model.*;
import nus.iss.team2.ADProjectTECHS.Model.Enums.SearchType;
import nus.iss.team2.ADProjectTECHS.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value={"/search", "/"})
public class SearchController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobSkillService jobSkillService;

    @Autowired
    private CourseCrawledService courseCrawledService;

    private List<CourseCrawled> currentList;
    @Autowired
    private MemberService memberService;



    @GetMapping("/skills/result")
    public String ViewSkillSearchResult(@RequestParam String job, Model model){

        Job job1 = jobService.findJobByJobTitle(job);
        List<Skill> skillList = skillService.findSkillsByJob(job1);

        //find member
        String currentUsername;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsername = authentication.getName();
        } else {

            //if not member
            model.addAttribute("skillList",skillList);
            return "Feature1-SearchSkills/skill-result";
        }

        Member currentMember = memberService.loadMemberByUsername(currentUsername);

        if (currentMember == null) throw new RuntimeException("cannot find current member");

        //if member
        model.addAttribute("skillList",skillList);
        model.addAttribute("member",currentMember);
        model.addAttribute("searchedjob",job);
        model.addAttribute("job1",job1);


        return "Feature1-SearchSkills/skill-result";
    }


    @GetMapping(value={"/skills/home", "","/"})
    public String SearchSkill(Model model){
        List<Job> jobList = jobService.findAll();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < jobList.size(); i++) {
            titles.add(jobList.get(i).getJobTitle());
        }

        model.addAttribute("titles",titles);

        return "Feature1-SearchSkills/search";
    }

    @GetMapping("/courses/home")
    public String showSearchPage(Model model) {

        List<Skill> skillList = skillService.findAll();

        List<String> titles = new ArrayList<>();
        for (int i = 0; i < skillList.size(); i++) {
            titles.add(skillList.get(i).getSkillTitle());
        }
        model.addAttribute("titles", titles);

        return "Feature2-SearchCourse/search";
    }

    @GetMapping("/courses/result")
    public String getSearchResult(@RequestParam(value="skill") String skill, Model model){

        Skill skill1 = skillService.findSkillByTitle(skill);

        List<CourseCrawled> courseCrawledList = courseCrawledService.findCoursesBySkillId(skill1.getSkillId());


        model.addAttribute("courseList", courseCrawledList);
        model.addAttribute("entered", skill);

        this.currentList = courseCrawledList;

        return "Feature2-SearchCourse/course-result";

    }

    @PostMapping("/fragment")
    public String getSearchResultFromHeader(@RequestParam(value="query") String query, RedirectAttributes redirectAttributes){
        
        Skill skill = skillService.findSkillByTitle(query);
        Job job = jobService.findJobByJobTitle(query);

        if (skill != null){
            redirectAttributes.addAttribute("skill", query);
           return "redirect:/courses/result";
        }
        if (job != null){
            redirectAttributes.addAttribute("job", query);
            return "redirect:/skills/result";
        }

        return ""; // need error mapping
    }

    @GetMapping("/sort/popular")
    public String sortByLikes(Model model){

        currentList.sort(Comparator.comparingInt(c -> (int) c.getLikes()));
        model.addAttribute("courseList", currentList);

        return "Feature2-SearchCourse/course-result";



    }

    @GetMapping("/sort/time")
    public String sortByPopular(Model model){

        currentList.sort(Comparator.comparing(CourseCrawled::getDate));
        model.addAttribute("courseList", currentList);

        return "Feature2-SearchCourse/course-result";
    }

    public List<String> getAllJobsAndSkillTitles(){
        List<String> titles = new ArrayList<>();
        List<String> jobTitles = jobService.findJobTitles();
        List<String> skillTitles = skillService.findSkillTitles();
        titles.addAll(jobTitles);
        titles.addAll(skillTitles);
        return titles;
    }

    @GetMapping("/duration/{i}")
    public String filterByVideoDuration(@PathVariable("i") int i, Model model) {

        List<CourseCrawled> result = new ArrayList<>();

        if (i == 1) {
            result = currentList.stream().filter(courseCrawled ->
                            courseCrawled.getDurationHours() < 1)
                    .collect(Collectors.toList());
        }

        if (i == 2) {
            result = currentList.stream().filter(courseCrawled ->
                            courseCrawled.getDurationHours() >= 1 && courseCrawled.getDurationHours() < 2)
                    .collect(Collectors.toList());
        }

        if (i == 3) {
            result = currentList.stream().filter(courseCrawled ->
                            courseCrawled.getDurationHours() >=2 )
                    .collect(Collectors.toList());
        }

        model.addAttribute("courseList", result);

        return "Feature2-SearchCourse/course-result";


    }






    // TESTING WEB CLIENT
    // @Autowired
    // WebClient client;
    
    // @GetMapping("")
    // public void findEmployee(){
        
    //     Flux<Employee> empListFlux = client.get()
    //         .uri("allcourses")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .retrieve()
    //         .bodyToFlux(Employee.class);

    //     List<Employee> empList = empListFlux.collectList().block();

    //     Mono<Employee> emp = client.get()
    //         .uri("/employee/1")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .retrieve()
    //         .bodyToMono(Employee.class);
    //     Employee employee = emp.block();

    //     System.out.println(employee);
    //     empList.stream().forEach(System.out::println);
    // }


//    @GetMapping(value = {"/search","/","home"})
//    public String Search(Model model){
//        model.addAttribute("query", new Query());
//        return "search";
//    }
//
//    @PostMapping("/search")
//    public String SearchRedirect(@ModelAttribute Query query,
//                                 RedirectAttributes redirectAttributes){
//
//        SearchType searchType = query.getSearchType();
//        String input = query.getQueryString().toLowerCase();
//
//        if (searchType.equals(SearchType.JOBS)) {
//            redirectAttributes.addAttribute("jobTitleEntered", input);
//            return "redirect:/skills/searchByJobTitle";
//        } else if (searchType.equals(SearchType.COURSES)){
//            redirectAttributes.addAttribute("courseTitleEntered", input);
//            return "redirect:/courses/searchByCourseTitle";
//        }
//
//        return "redirect:/search";
//
//    }
    
}
