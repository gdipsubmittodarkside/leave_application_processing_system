package nus.iss.team2.ADProjectTECHS.Controller;

import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;
import nus.iss.team2.ADProjectTECHS.Service.MyCourseService;
import nus.iss.team2.ADProjectTECHS.Service.ScheduleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleEventController {

    @Autowired
    private ScheduleEventService scheduleEventService;

    @GetMapping("/")
    public String getAllScheduleEvents(Model model){
        List<ScheduleEvent> scheduleEventList = scheduleEventService.getAllScheduleEvents();
        model.addAttribute("scheduleEventList", scheduleEventList);
        return null;
    }


    @GetMapping("/save")
    private String saveCourseToSchedule(@ModelAttribute("schedule") @Valid ScheduleEvent scheduleEvent,
                                        Model model) {
        scheduleEventService.createScheduleEvent(scheduleEvent);
        model.addAttribute("schedule", scheduleEvent);
        return "/";
    }

    @GetMapping("/update/{id}")
    private String updateCourseInSchedule(
            @PathVariable("id") Long id,
            @ModelAttribute("schedule")@Valid ScheduleEvent scheduleEvent,
            Model model) {
       ScheduleEvent scheduleEventExist = scheduleEventService.findScheduleEventById(id);
       scheduleEvent.setScheduleId(scheduleEventExist.getScheduleId());
       scheduleEventService.updateScheduleEvent(scheduleEvent);
       return "/";

    }

}
