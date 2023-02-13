package nus.iss.team2.ADProjectTECHS.Controller.RestController;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;

@RestController
@RequestMapping("/api")
public class RMemberController {
    
    @Autowired
    private MemberService memberService;


    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers(){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }

    @PostMapping("/members")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }


    @PutMapping("/members")
    public ResponseEntity<Member> updateMember(@RequestBody Member member){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Long> deleteMember(@PathVariable("id") Long id){


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id")Long id){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @GetMapping("/members/name")
    public ResponseEntity<List<Member>> getMemberByName(@RequestParam String name){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

      
    @GetMapping("/members/email/{email}")
	public ResponseEntity<Optional<Member>> getMemberByEmail(@PathVariable String email) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @GetMapping("/members/name/{name}")
	public ResponseEntity<List<Member>> getMemberByNameContaining(@PathVariable String name) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
