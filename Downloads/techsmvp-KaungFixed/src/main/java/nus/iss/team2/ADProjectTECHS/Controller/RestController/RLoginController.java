package nus.iss.team2.ADProjectTECHS.Controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;

@RestController
@RequestMapping("/api")
public class RLoginController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member member){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/signup")
    public ResponseEntity<Member> sign(@RequestBody Member member){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<Member> logout(@PathVariable("id")long id){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
