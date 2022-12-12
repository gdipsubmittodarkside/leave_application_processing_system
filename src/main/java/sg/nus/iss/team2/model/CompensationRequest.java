package sg.nus.iss.team2.model;

import java.time.LocalDateTime;

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
@Table(name="Compensation_Request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompensationRequest {

    @Id
    @Column(name="Compensation_leave_id")
    private Integer compensationLeaveId;


    @Column(name = "OT_start_time")
    private LocalDateTime OTstartTime;

    @Column(name="OT_end_time")
    private LocalDateTime OTendTime;

    @Column(name="OT_Period")
    private long OTPeriod;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
}
