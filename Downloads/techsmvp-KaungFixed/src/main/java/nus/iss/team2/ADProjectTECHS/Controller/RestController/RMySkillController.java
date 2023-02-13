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

import nus.iss.team2.ADProjectTECHS.Model.MySkill;
import nus.iss.team2.ADProjectTECHS.Service.MySkillService;

@RestController
@RequestMapping("/api")
public class RMySkillController {
    
    @Autowired
    private MySkillService msService;

    @GetMapping("/mySkills")
    public ResponseEntity<List<MySkill>> getAllMySkills(){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }

    @PostMapping("/mySkills")
    public ResponseEntity<MySkill> saveMySkill(@RequestBody MySkill mySkill){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }


    @PutMapping("/mySkills")
    public ResponseEntity<MySkill> updateMySkill(@RequestBody MySkill mySkill){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @DeleteMapping("/mySkills/{id}")
    public ResponseEntity<Long> deleteMySkill(@PathVariable("id") Long id){


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/mySkills/{id}")
    public ResponseEntity<MySkill> getMySkillById(@PathVariable("id")Long id){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}