package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import java.time.LocalDate;
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

import lombok.RequiredArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Service.JobSkillService;
@RestController
@RequestMapping("api/")
public class RJobSkillController {
    @Autowired
    private JobSkillService jobSkillService;

    @GetMapping("/jobSkills")
    public ResponseEntity<List<Skill>> getAllJobSkills(){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/jobSkills")
    public ResponseEntity<JobSkill> saveJobSkill(@RequestBody JobSkill jobSkill){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/jobSkills")
    public ResponseEntity<JobSkill> updateJobSkill(@RequestBody Skill skill){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/jobSkills/{id}")
    public ResponseEntity<Long> deleteJobSkill(@PathVariable("id") Long id){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/jobSkills/{id}")
    public ResponseEntity<Skill> getJobSkillById(@PathVariable("id")Long id){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/jobSkills/skill")
    public ResponseEntity<List<JobSkill>> getJobSkillBySkill(@RequestBody Skill skill){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @GetMapping("/jobSkills/job")
    public ResponseEntity<List<JobSkill>> getJobSkillByJob(@RequestBody Job job){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/jobSkills/{date}")
    public ResponseEntity<List<JobSkill>> getJobSkillByDate(@PathVariable("date") LocalDate date){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    


}