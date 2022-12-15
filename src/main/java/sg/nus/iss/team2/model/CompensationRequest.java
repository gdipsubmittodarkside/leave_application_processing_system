package sg.nus.iss.team2.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compensationLeaveId;


    @Column(name = "OT_start_time")
    private LocalDateTime OTstartTime;

    @Column(name="OT_end_time")
    private LocalDateTime OTendTime;

    private String description;
    
    private String comment;

    @Column(name="status", columnDefinition = "ENUM('APPLIED', 'WITHDRAW', 'APPROVED', 'REJECTED', 'UPDATED', 'PENDING')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public CompensationRequest(LocalDateTime oTstartTime, LocalDateTime oTendTime, LeaveStatusEnum status, Employee employee) {
        OTstartTime = oTstartTime;
        OTendTime = oTendTime;
        this.status = status;
        this.employee = employee;
    }
}
