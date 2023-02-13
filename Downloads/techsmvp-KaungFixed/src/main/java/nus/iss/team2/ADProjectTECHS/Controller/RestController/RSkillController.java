package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import java.util.ArrayList;
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

import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;

@RestController
@RequestMapping("/api")
public class RSkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/skills/title")
    public ResponseEntity<List<String>> getAllSkills() {
        try {
            List<Skill> skills = new ArrayList<Skill>();
            skills = skillService.getAllSkills();
            if (skills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<String> titles = (List<String>) skills.stream().map(s -> s.getSkillTitle());
            return new ResponseEntity<>(titles, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/skills")
    public ResponseEntity<Skill> saveSkill(@RequestBody Skill skill) {

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/skills")
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Long> deleteSkill(@PathVariable("id") Long id) {

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/skills/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}