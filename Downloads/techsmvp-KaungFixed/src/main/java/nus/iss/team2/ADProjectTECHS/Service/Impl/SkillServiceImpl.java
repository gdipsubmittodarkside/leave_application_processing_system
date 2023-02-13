package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Repository.SkillRepository;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;

@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepo;


    @Override
    public Skill findSkillById(Long id) {
        // TODO Auto-generated method stub
        return skillRepo.findSkillById(id);
    }

    @Override
    public Skill createSkill(Skill skill) {
        // TODO Auto-generated method stub
        return skillRepo.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteSkill(Long id) {
        // TODO Auto-generated method stub
        try {
            skillRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Skill> getAllSkills() {
        // TODO Auto-generated method stub
        return skillRepo.findAll();
    }

    @Override
    public List<Skill> findSkillsByJob(Job job) {
        return skillRepo.findSkillsByJob(job);
    }

    @Override
    public Skill findSkillByTitle(String skill) {
        return skillRepo.findSkillBySkillTitle(skill).orElse(null);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepo.findAll();
    }

    @Override
    public List<String> findSkillTitles() {
        List<Skill> skillList = skillRepo.findAll();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < skillList.size(); i++) {
            titles.add(skillList.get(i).getSkillTitle());
        }
        return titles;
    }

}
