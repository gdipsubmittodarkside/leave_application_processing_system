package nus.iss.team2.ADProjectTECHS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill,Long>{

    Optional<JobSkill> findById(Long aLong);


    List<JobSkill> findJobSkillByJobAndAndSkill(Job job, Skill skill);


    @Query("select js from JobSkill js where js.job.jobId= :jobId")
    List<JobSkill> findSkillsByJobId(@Param("jobId") Long jobId);
}
