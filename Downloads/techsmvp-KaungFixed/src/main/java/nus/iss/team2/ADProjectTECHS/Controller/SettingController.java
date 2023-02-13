package nus.iss.team2.ADProjectTECHS.Controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Model.MySkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Service.JobService;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;
import nus.iss.team2.ADProjectTECHS.Service.MySkillService;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;
import nus.iss.team2.ADProjectTECHS.Utility.MemberUtils;

@Controller
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private MySkillService mySkillService;

    // private Long userId = -1l;


    private PasswordEncoder passwordEncoder;

    public SettingController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = {"/",""})
    public String showSettingsPage(Model model){


        //find member
        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);
        model.addAttribute("currentAvatar", currentMember.getAvatar());
        model.addAttribute("currentMember", currentMember);


        if (currentMember == null) throw new RuntimeException("cannot find current member");


        model.addAttribute("member", new Member());
        List<Job> jobList = jobService.findAll();
        model.addAttribute("jobList", jobList);

        List<Skill> skillList = skillService.findAll();
        model.addAttribute("skillList", skillList);

        return "Others/settings";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("member") Member newMember, RedirectAttributes redirectAttributes,Model model){
        // Member currentMember = getCurrentMember(userId);

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if (newMember != null) {

//            if (newMember.getUsername()!=null && newMember.getUsername().trim()!="") {
//                currentMember.setUsername(newMember.getUsername());
//
//            }

//            if (newMember.getEmail() != null && newMember.getEmail().trim() != "" ) {
//                currentMember.setEmail(newMember.getEmail());
//            }

            if (newMember.getCurrentJobTitle()!=null && newMember.getCurrentJobTitle().trim()!="") {
                currentMember.setCurrentJobTitle(newMember.getCurrentJobTitle());
            }

            if (newMember.getBirthday()!=null) {
                currentMember.setBirthday(newMember.getBirthday());
            }

            if (newMember.getShortBio()!=null && newMember.getShortBio().trim()!="") {
                currentMember.setShortBio(newMember.getShortBio());
            }

            if (newMember.getDreamJob()!=null) {
                currentMember.setDreamJob(newMember.getDreamJob());
            }


            memberService.save(currentMember);

        }

        model.addAttribute("message", "saved profile");


//        if (newMember.getUsername()!=null && newMember.getUsername().trim()!="") {
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(currentMember.getUsername());
//            MemberUtils.setLoginUser(userDetails);
//
//            return "redirect:/logout";
//        }

        return "redirect:/settings";

    }

    @PostMapping("/information")
    public String updateInfo(@ModelAttribute("member") Member newMember, @RequestParam("skills") List<Integer> skillsIds , RedirectAttributes redirectAttributes, Model model){
        // Member currentMember = getCurrentMember(userId);

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if (skillsIds.size()>0) {
            List<Skill> skillList = new ArrayList<>();
            for (int i = 0; i < skillsIds.size(); i++) {
                Skill skill = skillService.findSkillById(Long.valueOf(skillsIds.get(i)));
                MySkill mySkill = new MySkill();
                if (mySkillService.findMySkillByMemberAndSkill(currentMember, skill) == null){
                    mySkill.setSkill(skill);
                    mySkill.setMember(currentMember);
                    mySkillService.save(mySkill);
                    currentMember.getMySkills().add(mySkill);
                }

            }
        }


        if (newMember!=null) {

            if (newMember.getGender() != null) {
                currentMember.setGender(newMember.getGender());
            }
            if (newMember.getEducation() != null) {
                currentMember.setEducation(newMember.getEducation());
            }

            memberService.save(currentMember);
        }

        model.addAttribute("message", "save info");

        return "redirect:/settings";

    }


    @PostMapping("/password")
    public String updatePassword(
            @RequestParam("curPwd")String curPwd,
            @RequestParam("newPwd")String newPwd,
            RedirectAttributes ra,
            Model model){
        // Member currentMember = getCurrentMember(userId);
        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if (curPwd.equals(newPwd)) {
            model.addAttribute("message", "Your new password must be different than the old one.");
            model.addAttribute("member" ,new Member());
            return "settings";

        }
        if (!passwordEncoder.matches(curPwd, currentMember.getPassword())) {
            model.addAttribute("message", "Your old password is incorrect.");
            model.addAttribute("member" ,new Member());
            return "settings";

        } else {

            memberService.changePassword(currentMember, newPwd);
            model.addAttribute("message","You have changed your password successfully. Please login again." );
            return "redirect:/logout";
        }
    }



    @RequestMapping("/image")
    public String uploadImage(Model model,@RequestParam("image")MultipartFile file) {

        String currentUsername = MemberUtils.getMemberFromSpringSecurity();

        Member currentMember = memberService.loadMemberByUsername(currentUsername);


        if(file.isEmpty()) throw new RuntimeException("upload image fail");

        // change to  your static path!!!
        String currentDir = System.getProperty("user.dir");
        String staticPath = currentDir + "/src/main/resources/static";
        String filename = file.getOriginalFilename();
        String url_path = "images/avatar" + File.separator + filename;
        String savePath = staticPath + File.separator + url_path;
        String visitPath = File.separator + url_path;


        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentMember.setAvatar(visitPath);
        memberService.save(currentMember);

        return "redirect:/settings";
    }


//
//    @GetMapping(value = {"/",""})
//    public String showSettingsPage(Model model){
//
//        // if (userId < 0) {
//        //     Member currentMember = getMemberFromSpringSecurity();
//        //     userId = currentMember.getMemberId();
//        // }
//
//         //find member
//        String currentUsername = MemberUtils.getMemberFromSpringSecurity();
//
//        Member currentMember = memberService.loadMemberByUsername(currentUsername);
//
//
//        if (currentMember == null) throw new RuntimeException("cannot find current member");
//
//        model.addAttribute("member", new Member());
//        List<Job> jobList = jobService.findAll();
//        model.addAttribute("jobList", jobList);
//
//        List<Skill> skillList = skillService.findAll();
//        model.addAttribute("skillList", skillList);
//
//        return "Others/settings";
//    }
//
//    @PostMapping("/profile")
//    public String updateProfile(@ModelAttribute("member") Member newMember, RedirectAttributes redirectAttributes,Model model){
//        // Member currentMember = getCurrentMember(userId);
//
//        String currentUsername = MemberUtils.getMemberFromSpringSecurity();
//
//        Member currentMember = memberService.loadMemberByUsername(currentUsername);
//
//
//        if (newMember != null) {
//
//            if (newMember.getUsername()!=null && newMember.getUsername().trim()!="") {
//                currentMember.setUsername(newMember.getUsername());
//
//            }
//
//            if (newMember.getEmail() != null && newMember.getEmail().trim() != "" ) {
//                currentMember.setEmail(newMember.getEmail());
//            }
//
//            if (newMember.getCurrentJobTitle()!=null && newMember.getCurrentJobTitle().trim()!="") {
//                currentMember.setCurrentJobTitle(newMember.getCurrentJobTitle());
//            }
//
//            if (newMember.getBirthday()!=null) {
//                currentMember.setBirthday(newMember.getBirthday());
//            }
//
//            if (newMember.getShortBio()!=null && newMember.getShortBio().trim()!="") {
//                currentMember.setShortBio(newMember.getShortBio());
//            }
//
//            if (newMember.getDreamJob()!=null) {
//                currentMember.setDreamJob(newMember.getDreamJob());
//
//            }
//
//
//            memberService.save(currentMember);
//
//        }
//
//        model.addAttribute("message", "saved profile");
//
//
//        if (newMember.getUsername()!=null && newMember.getUsername().trim()!="") {
//
//            return "redirect:/logout";
//        }
//
//        return "redirect:/settings";
//
//    }
//
//    @PostMapping("/information")
//    public String updateInfo(@ModelAttribute("member") Member newMember, @RequestParam("skills") List<Integer> skillsIds , RedirectAttributes redirectAttributes, Model model){
//        // Member currentMember = getCurrentMember(userId);
//
//        String currentUsername = MemberUtils.getMemberFromSpringSecurity();
//
//        Member currentMember = memberService.loadMemberByUsername(currentUsername);
//
//
//        if (skillsIds.size()>0) {
//            List<Skill> skillList = new ArrayList<>();
//            for (int i = 0; i < skillsIds.size(); i++) {
//                Skill skill = skillService.findSkillById(Long.valueOf(skillsIds.get(i)));
//                MySkill mySkill = new MySkill();
//                if (mySkillService.findMySkillByMemberAndSkill(currentMember, skill) == null){
//                    mySkill.setSkill(skill);
//                    mySkill.setMember(currentMember);
//                    mySkillService.save(mySkill);
//                    currentMember.getMySkills().add(mySkill);
//                }
//
//            }
//        }
//
//
//        if (newMember!=null) {
//
//            if (newMember.getGender() != null) {
//                currentMember.setGender(newMember.getGender());
//            }
//            if (newMember.getEducation() != null) {
//                currentMember.setEducation(newMember.getEducation());
//            }
//
//            memberService.save(currentMember);
//        }
//
//        model.addAttribute("message", "save info");
//
//        return "redirect:/settings";
//
//    }
//
//
//    @PostMapping("/password")
//    public String updatePassword(
//            @RequestParam("curPwd")String curPwd,
//            @RequestParam("newPwd")String newPwd,
//            RedirectAttributes ra,
//            Model model){
//        // Member currentMember = getCurrentMember(userId);
//        String currentUsername = MemberUtils.getMemberFromSpringSecurity();
//
//        Member currentMember = memberService.loadMemberByUsername(currentUsername);
//
//
//        if (curPwd.equals(newPwd)) {
//            model.addAttribute("message", "Your new password must be different than the old one.");
//            model.addAttribute("member" ,new Member());
//            return "settings";
//
//        }
//        if (!passwordEncoder.matches(curPwd, currentMember.getPassword())) {
//            model.addAttribute("message", "Your old password is incorrect.");
//            model.addAttribute("member" ,new Member());
//            return "settings";
//
//        } else {
//
//            memberService.changePassword(currentMember, newPwd);
//            model.addAttribute("message","You have changed your password successfully. Please login again." );
//            return "redirect:/logout";
//        }
//    }
//
//
//
//
//    @RequestMapping("/image")
//    @ResponseBody
//    public String uploadImage(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
//        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
//        String filename = file.getOriginalFilename();
//        String url_path = "images/avatar" + File.separator + filename;
//        String savePath = staticPath + File.separator + url_path;
//        String visitPath = "static/" + url_path;
//        File saveFile = new File(savePath);
//        if (!saveFile.exists()){
//            saveFile.mkdir();
//        }
//        try {
//            file.transferTo(saveFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return visitPath;
//    }
//
//
//    public Member getCurrentMember(Long id) {
//        return memberService.findById(id);
//    }

}
