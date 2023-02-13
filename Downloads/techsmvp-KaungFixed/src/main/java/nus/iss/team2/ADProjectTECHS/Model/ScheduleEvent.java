package nus.iss.team2.ADProjectTECHS.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "schedule_event")
@Data
@AllArgsConstructor
public class ScheduleEvent {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleId;
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String txtColor;

    private String bgColor;

    private String notes;

    @OneToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "my_course_id")
    private MyCourse myCourse;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScheduleEvent that = (ScheduleEvent) o;
        return scheduleId == that.scheduleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId);
    }
}