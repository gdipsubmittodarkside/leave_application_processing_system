package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Service.CourseCrawledService;

@RestController
@RequestMapping("/api")
public class RCourseController {

    @Autowired
    private CourseCrawledService courseCrawledService;
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
        @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
                @Content(examples = { @ExampleObject(value = "") }) }),
        @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}", content = {
                @Content(examples = { @ExampleObject(value = "") }) }) })
    @GetMapping("/courses")
    public ResponseEntity<List<CourseCrawled>> getAllCourses() {

        try {
            List<CourseCrawled> coursesCrawled = new ArrayList<CourseCrawled>();
            coursesCrawled = courseCrawledService.getCourseCrawledList();

            if (coursesCrawled.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(coursesCrawled, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseCrawled> saveCourse(@RequestBody CourseCrawled courseCrawled) {

        try {
            CourseCrawled savedCourseCrawled = courseCrawledService.saveCourseCrawled(courseCrawled);
            return new ResponseEntity<>(savedCourseCrawled, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseCrawled> updateCourse(@PathVariable("id") long id,
            @RequestBody CourseCrawled courseCrawled) {

        try {

            Optional<CourseCrawled> optCourseCrawled = courseCrawledService.findCourseCrawled(id);

            if (optCourseCrawled.isPresent()) {
                CourseCrawled course = optCourseCrawled.get();
                course.setCourseTitle(courseCrawled.getCourseTitle());
                course.setLikes(courseCrawled.getLikes());
                course.setSubscribers(courseCrawled.getSubscribers());
                course.setUrlLink(courseCrawled.getUrlLink());
                course.setViews(courseCrawled.getViews());
                course.setSkill(courseCrawled.getSkill());

                CourseCrawled savedCourseCrawled = courseCrawledService.updateCourseCrawled(course);

                return new ResponseEntity<CourseCrawled>(savedCourseCrawled, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable("id") Long id) {

        try {

            var isRemoved = courseCrawledService.deleteCourseCrawledById(id);

            if (!isRemoved) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/views")
    public ResponseEntity<List<CourseCrawled>> getCoursesSortedByViews() {

        try {
            List<CourseCrawled> courseCrawleds = courseCrawledService.findCoursesSortedByViews();
            return new ResponseEntity<>(courseCrawleds, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/courses/likes")
    public ResponseEntity<List<CourseCrawled>> getCoursesByLikes() {

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/courses/skill")
    public ResponseEntity<List<CourseCrawled>> getCoursesBySkill(@RequestBody Skill skill) {

        try {
            List<CourseCrawled> coursesCrawled = courseCrawledService.findCoursesBySkill(skill);
            return new ResponseEntity<>(coursesCrawled, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // find courses by title keyword ignoring case
    // @GetMapping("/courses/{title}")
    // public ResponseEntity<List<CourseCrawled>> getCoursesByTitleLike(@PathVariable("title") String title) {

    //     try {

    //         List<CourseCrawled> coursesCrawled = courseCrawledService.findCoursesTitleLike(title);
    //         return new ResponseEntity<>(coursesCrawled, HttpStatus.OK);

    //     } catch (Exception e) {

    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }

    // }

    @GetMapping("/courses/sortBySubscribers")
    public ResponseEntity<List<CourseCrawled>> getCoursesBySubscribers() {

        try {
            List<CourseCrawled> coursesCrawled = courseCrawledService.findCoursesSortedBySubscribers();

            return new ResponseEntity<>(coursesCrawled, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/courses/{skillTitle}",method = RequestMethod.GET)
    public ResponseEntity<List<CourseCrawled>> getCoursesBySkillTitleLike(
            @PathVariable("skillTitle") String skillTitle) {

        try {

            skillTitle = skillTitle.toLowerCase();
            List<CourseCrawled> courseCrawleds = courseCrawledService
                    .findCoursesBySkillTitleLike(skillTitle);

            return new ResponseEntity<>(courseCrawleds, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
