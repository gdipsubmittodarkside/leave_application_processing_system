package nus.iss.team2.ADProjectTECHS.Controller;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.ScheduleEvent;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;
import nus.iss.team2.ADProjectTECHS.Service.MyCourseService;
import nus.iss.team2.ADProjectTECHS.Service.ScheduleEventService;
import nus.iss.team2.ADProjectTECHS.Utility.MemberUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private ScheduleEventService scheduleEventService;

    @Autowired
    private MyCourseService myCourseService;

    @Autowired
    private MemberService memberService;




    // READ
    @GetMapping(value = {"/",""})
    public String ViewCalendar(Model model) {

        // from spring security context get member

        // String currentUsername;
        // currentUsername = MemberUtils.getMemberFromSpringSecurity();


        // if (userId < 0) {
        //     String username = MemberUtils.getMemberFromSpringSecurity();
        //     Member currentMember = memberService.loadMemberByUsername(username);
        //     userId = currentMember.getMemberId();
        // }

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);

        if (currentMember == null) throw new RuntimeException("cannot find current member");

        List<ScheduleEvent> events = scheduleEventService.findScheduleEventByMemberId(currentMember.getMemberId());


        JSONArray jsonArray = new JSONArray();

        for(int i=0; i<events.size();i++){
            JSONObject jo = new JSONObject();
            jo.put("s", events.get(i).getStartDate());
            jo.put("e", events.get(i).getEndDate());
            jo.put("t", events.get(i).getNotes());
            jo.put("c", events.get(i).getTxtColor());
            jo.put("b", events.get(i).getBgColor());
            jo.put("course", events.get(i).getMyCourse().getMyCourseTitle());
            jo.put("id",events.get(i).getScheduleId());
            jsonArray.put(jo);
        }
        // Member currentMember = memberService.findById(userId);

        List<MyCourse> myCoursesS = new ArrayList<>();
        List<MyCourse> myCoursesUns = new ArrayList<>();
        List<MyCourse> myCourseList = myCourseService.getMyCoursesByMemberId(currentMember.getMemberId());

        for (int i = 0; i < myCourseList.size(); i++) {
            if (myCourseList.get(i).getScheduleEvent()!=null) {
                myCoursesS.add(myCourseList.get(i));
            } else if (myCourseList.get(i).getScheduleEvent()==null){
                myCoursesUns.add(myCourseList.get(i));
            }
        }

        model.addAttribute("schedule_id", scheduleEventService.findScheduleEventByMemberId(currentMember.getMemberId()).size());
        model.addAttribute("events", jsonArray);
        model.addAttribute("unscheduledCourseList", myCoursesUns);
        model.addAttribute("scheduledCourseList", myCoursesS);
        model.addAttribute("myCourseS",myCoursesS);


        return "Feature3-Dashboard/calendar";
    }

    // CREATE
    @PostMapping("/create")
    public String CreateScheduleEvent(@RequestParam("s") String startDate,
                                      @RequestParam("e") String endDate,
                                      @RequestParam("c") String courseTitle,
                                      @RequestParam("cx") String txtColor,
                                      @RequestParam("b") String bgColor,
                                      @RequestParam("t") String txtNote
    ){

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        MyCourse myCourse = myCourseService.findMyCourseByTitle(courseTitle,currentMember.getMemberId());
        // startDate = startDate.substring(0, startDate.indexOf("T"));
        // endDate = endDate.substring(0, endDate.indexOf("T"));
        LocalDateTime sd = LocalDateTime.parse(startDate);
        LocalDateTime ed = LocalDateTime.parse(endDate);



        if ((scheduleEventService.findScheduleEventByMemberAndMyCourse(memberService.findById(currentMember.getMemberId()), myCourse) == null)) {
            ScheduleEvent scheduleEvent = new ScheduleEvent();
            scheduleEvent.setMember(memberService.findById(currentMember.getMemberId()));
            scheduleEvent.setStartDate(sd);
            scheduleEvent.setEndDate(ed);
            scheduleEvent.setBgColor(bgColor);
            scheduleEvent.setTxtColor(txtColor);
            scheduleEvent.setNotes(txtNote);
            myCourse.setScheduleEvent(scheduleEvent);
            scheduleEvent.setMyCourse(myCourse);
            myCourseService.updateMyCourse(myCourse, myCourse.getMyCourseId());
            scheduleEventService.createScheduleEvent(scheduleEvent);
        }else{
            ScheduleEvent scheduleEvent = scheduleEventService.findScheduleEventByMemberAndMyCourse(currentMember, myCourse);
            scheduleEvent.setBgColor(bgColor);
            scheduleEvent.setTxtColor(txtColor);
            scheduleEvent.setStartDate(sd);
            scheduleEvent.setEndDate(ed);
            scheduleEvent.setNotes(txtNote);
            scheduleEventService.updateScheduleEvent(scheduleEvent);
        }


        return "redirect:/calendar";

    }

    // DELETE
    @PostMapping("/delete")
    public String DeleteScheduleEvent(@RequestParam("s") String startDate,
                                      @RequestParam("e") String endDate,
                                      @RequestParam("c") String courseTitle,
                                      @RequestParam("cx") String txtColor,
                                      @RequestParam("b") String bgColor,
                                      @RequestParam("t") String txtNote
    ){

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        Member cm = memberService.findById(currentMember.getMemberId());

        MyCourse myCourse = myCourseService.findMyCourseByTitle(courseTitle,currentMember.getMemberId());

        ScheduleEvent se = scheduleEventService.findScheduleEventByMemberAndMyCourse(cm, myCourse);
        myCourse.setScheduleEvent(null);
        myCourseService.updateMyCourse(myCourse, myCourse.getMyCourseId());

        if (se == null) {
            throw new RuntimeException("cannot find xx");
        }

        scheduleEventService.delete(se);

        return "redirect:/calendar";
    }



}