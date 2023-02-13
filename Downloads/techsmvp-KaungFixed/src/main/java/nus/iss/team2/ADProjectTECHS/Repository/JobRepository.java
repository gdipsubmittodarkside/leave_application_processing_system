package nus.iss.team2.ADProjectTECHS.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.Job;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {



    Optional<Job> findJobByJobTitle(String jobTitle);

    List<Job> findAllByJobTitleContainingIgnoreCase(String jobTitle);



}
