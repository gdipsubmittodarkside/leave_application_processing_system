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

import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;
import nus.iss.team2.ADProjectTECHS.Service.ScheduleEventService;

@RestController
@RequestMapping("/api")
public class RScheduleEventController {

    
@Autowired
private ScheduleEventService scheduleEventService;

    @GetMapping("/scheduleEvents")
    public ResponseEntity<List<ScheduleEvent>> getAllScheduleEvents(){




        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }

    @PostMapping("/scheduleEvents")
    public ResponseEntity<ScheduleEvent> saveScheduleEvent(@RequestBody ScheduleEvent scheduleEvent){


        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);   
        
    }


    @PutMapping("/scheduleEvents")
    public ResponseEntity<ScheduleEvent> updateScheduleEvent(@RequestBody ScheduleEvent scheduleEvent){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @DeleteMapping("/scheduleEvents/{id}")
    public ResponseEntity<Long> deleteScheduleEvent(@PathVariable("id") Long id){


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/scheduleEvents/{id}")
    public ResponseEntity<ScheduleEvent> getScheduleEventById(@PathVariable("id")Long id){

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }






    
}
