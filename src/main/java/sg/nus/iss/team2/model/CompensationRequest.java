package sg.nus.iss.team2.model;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="leave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompensationRequest {

    @Id
    @Column(name="Compensation_leave_id")
    private Integer compensationLeaveId;


    @Column(name = "OT_start_time")
    private LocalDate OTstartTime;

    @Column(name="OT_end_time")
    private LocalDate OTendTime;

    @Column(name="status")
    private long OTPeriod = Duration.between(OTstartTime, OTendTime).toHours();

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    
}
