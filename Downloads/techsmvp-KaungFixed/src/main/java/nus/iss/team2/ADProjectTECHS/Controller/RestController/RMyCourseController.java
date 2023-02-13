package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Service.MyCourseService;

@RestController
@RequestMapping("/api")
public class RMyCourseController {

    @Autowired
    private MyCourseService myCourseService;
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
        @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
                @Content(examples = { @ExampleObject(value = "") }) }),
        @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}", content = {
                @Content(examples = { @ExampleObject(value = "") }) }) })
    @GetMapping("/myCourses")
    public ResponseEntity<List<MyCourse>> getAllMyCourses() {

        try {
            List<MyCourse> myCourses = myCourseService.getAllMyCourses();
            if (myCourses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(myCourses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/myCourses")
    public ResponseEntity<MyCourse> saveMyCourse(@RequestBody MyCourse mycourse) {
        try {
            MyCourse course = myCourseService.createMyCourse(mycourse);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/myCourses")
    public ResponseEntity<MyCourse> updateMyCourse(@RequestBody MyCourse mycourse) {

        try {
            MyCourse course = myCourseService.updateMyCourse(mycourse, mycourse.getMyCourseId());
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/myCourses/{id}")
    public ResponseEntity<Long> deleteMyCourse(@PathVariable("id") Long id) {

        try {
            var isRemoved = myCourseService.deleteMyCourse(id);
            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/myCourses/{id}")
    public ResponseEntity<MyCourse> getMyCourseById(@PathVariable("id") Long id) {
        try {
            MyCourse course = myCourseService.findMyCourseById(id);
            if(course == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}