package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import java.util.List;

import nus.iss.team2.ADProjectTECHS.Repository.JobRepository;
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

import lombok.RequiredArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Service.JobService;

@RestController
@RequestMapping("/api")
public class RJobController {
    
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> saveJob(@RequestBody Job job){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }

    @GetMapping("/jobs/title")
    public ResponseEntity<List<String>> getAllJobsTitle(){
        List<String> titles = jobService.findJobTitles();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @PutMapping("/jobs")
    public ResponseEntity<Job> updateJob(@RequestBody Job job){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Long> deleteJob(@PathVariable("id") Long id){


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id")Long id){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
