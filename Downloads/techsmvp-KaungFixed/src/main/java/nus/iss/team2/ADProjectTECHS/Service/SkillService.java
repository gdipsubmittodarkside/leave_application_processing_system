package nus.iss.team2.ADProjectTECHS.Service;

import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.Skill;


public interface SkillService {
    Skill findSkillById(Long id);
    Skill createSkill(Skill skill);
    Skill updateSkill(Skill skill, Long id);
    Boolean deleteSkill(Long id);
    List<Skill> getAllSkills();

    List<Skill> findSkillsByJob(Job job);
    Skill findSkillByTitle(String skill);


    List<Skill> findAll();

    List<String> findSkillTitles();
}
