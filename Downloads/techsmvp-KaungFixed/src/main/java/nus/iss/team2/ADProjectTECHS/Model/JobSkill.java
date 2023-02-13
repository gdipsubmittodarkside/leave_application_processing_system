package nus.iss.team2.ADProjectTECHS.Model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="job_skill")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSkill{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_skill_id")
    private long jobSkillId;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(cascade = { 
        CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name="last_updated_date")
    private LocalDate lastUpdatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSkill jobSkill = (JobSkill) o;
        return jobSkillId == jobSkill.jobSkillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobSkillId);
    }
}