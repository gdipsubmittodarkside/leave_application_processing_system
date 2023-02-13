package nus.iss.team2.ADProjectTECHS.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "my_course")
@AllArgsConstructor
public class MyCourse implements Serializable {

    @Id
    @Column(name = "my_course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long myCourseId;

    @Column(name = "progress")
    private int progress;

    @OneToOne(mappedBy = "myCourse", cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "schedule_id")
    private ScheduleEvent scheduleEvent;

    @Column(name = "my_course_title")
    private String myCourseTitle;

    @JsonProperty("skill_id")
    // @OneToOne
    // @JoinColumn(name = "skill_id")
    private Long skill;

    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

    @Column(name="course_url")
    private String courseUrl;

    private String thumbnail;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyCourse myCourse = (MyCourse) o;
        return myCourseId == myCourse.myCourseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myCourseId);
    }
}