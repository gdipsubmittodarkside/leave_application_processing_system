package nus.iss.team2.ADProjectTECHS.Service;

import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;


public interface JobSkillService {
    JobSkill findJobSkillById(Long id);
    JobSkill createJobSkill(JobSkill jobSkill);

    JobSkill updateJobSkill(JobSkill jobSkill, Long id);
    Boolean deleteJobSkill(Long id);
    List<JobSkill> getAllJobSkill();

    List<JobSkill> findSkillsByJobId(Long jobId);

    List<Skill> findSkillsReq(Long jobId);






}
