package nus.iss.team2.ADProjectTECHS.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nus.iss.team2.ADProjectTECHS.Model.Enums.Education;
import nus.iss.team2.ADProjectTECHS.Model.Enums.Gender;
import nus.iss.team2.ADProjectTECHS.Model.Enums.SearchType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "member")
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private long memberId;

    @Column(nullable = false)
    private String username;

    private String password;

    @OneToOne
    @JoinColumn(name = "dream_job_id")
    private Job dreamJob;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    @Enumerated(EnumType.STRING)
    private Education education;

    @Column(name = "current_job_title")
    private String currentJobTitle;

    @Column(name = "short_bio")
    private String shortBio;

    private String avatar;

    @OneToMany(mappedBy = "member")
    private List<ScheduleEvent> scheduleEvents;

    @OneToMany(mappedBy = "member")
    private List<MyCourse> myCourses;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<MySkill> mySkills;

    @Column(name = "course_taken")
    private String courseTaken;

    @Column(name = "current_city")
    private String currentCity;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}