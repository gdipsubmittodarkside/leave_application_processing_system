package nus.iss.team2.ADProjectTECHS.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "skill")
@AllArgsConstructor
public class Skill implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long skillId;

    @Column(name = "url_link")
    private String urlLink;

    @Column(name = "skill_title")
    private String skillTitle;

    @Column(name = "skill_description", length=10000)
    private String skillDescription;

    // @OneToOne(mappedBy = "skill")
    // private MyCourse myCourse;
    

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Skill skill = (Skill) o;
        return skillId == skill.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId);
    }
}