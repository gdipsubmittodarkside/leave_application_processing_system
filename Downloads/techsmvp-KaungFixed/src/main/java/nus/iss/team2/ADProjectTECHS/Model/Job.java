package nus.iss.team2.ADProjectTECHS.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
@Table(name = "job")
public class Job  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_id")
    private long jobId;

    @Column(name="job_title")
    private String jobTitle;

    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId == job.jobId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}