package nus.iss.team2.ADProjectTECHS.Service;

import java.util.List;

import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.Skill;


public interface JobService {
    Job findJobById(Long id);
    Job createJob(Job job);
    Job updateJob(Job job, Long id);
    boolean deleteJob(Long id);
    List<Job> getAllJobs();

    Job findJobByJobTitle(String jobTitle);

    List<Job> findJobByJobTitleLike(String jobTitle);

    List<String> findJobTitles();

    List<Job> findAll();
}
