package nus.iss.team2.ADProjectTECHS.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "my_skill")
@AllArgsConstructor
public class MySkill  {

    @Id
    @Column(name = "my_skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mySkillId;

    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "course_taken")
    private String courseTaken;

    

    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySkill mySkill = (MySkill) o;
        return mySkillId == mySkill.mySkillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mySkillId);
    }
}