package nus.iss.team2.ADProjectTECHS.Repository;

import java.util.List;
import java.util.Optional;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.Skill;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

    @Query("select j from Job j where j.jobTitle = :jobTitle")
    List<Skill> findSkillsByJobTitle(@Param("jobTitle") String jobTitle);

    @Query("select s from Skill s where s.skillId = :id")
    Skill findSkillById(@Param("id") Long id);

    @Query("select s from Skill s join JobSkill js on js.skill.skillId = s.skillId where js.job = :job")
    List<Skill> findSkillsByJob(@Param("job") Job job);

    @Query("select s from Skill s where s.skillTitle = :title")
    Optional<Skill> findSkillBySkillTitle(@Param("title") String title);
}
