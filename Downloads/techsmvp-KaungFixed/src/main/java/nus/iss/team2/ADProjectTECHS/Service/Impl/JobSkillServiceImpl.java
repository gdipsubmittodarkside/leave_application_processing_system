package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Repository.JobSkillRepository;
import nus.iss.team2.ADProjectTECHS.Service.JobSkillService;

@Service
public class JobSkillServiceImpl implements JobSkillService{

    @Autowired
    private JobSkillRepository jsRepo;

    @Override
    public List<JobSkill> findSkillsByJobId(Long jobId){
        return jsRepo.findSkillsByJobId(jobId);
    }

    @Override
    public List<Skill> findSkillsReq(Long jobId){
        List<Skill> skills = new ArrayList<>();
        List<JobSkill> jobSkills = findSkillsByJobId(jobId);
        for(JobSkill jsk: jobSkills){
            Skill sk = jsk.getSkill();
            skills.add(sk);
        }

        return skills;
    };


    @Override
    public JobSkill findJobSkillById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JobSkill createJobSkill(JobSkill jobSkill) {
        // TODO Auto-generated method stub
        return  jsRepo.save(jobSkill);
    }

    @Override
    public JobSkill updateJobSkill(JobSkill jobSkill, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteJobSkill(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<JobSkill> getAllJobSkill() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
