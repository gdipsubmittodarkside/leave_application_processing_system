package sg.nus.iss.team2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="leave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

    @Id
    @Column(name="leave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;


    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name = "leave_type", columnDefinition = "ENUM('ANNUAL', 'MEDICAL', 'COMPENSATION')")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum leaveType;

    @Column(name="reason" )
    private String reason;

    @Column(name="status", columnDefinition = "ENUM('APPLIED', 'CANCELLED', 'APPROVED', 'REJECTED', 'UPDATED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    @Column(name="comment" )
    private String comment;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;

    public Leave(LocalDate startDate, LocalDate endDate, LeaveTypeEnum leaveType, String reason,
            LeaveStatusEnum status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reason = reason;
        this.status = status;
    }

    public Leave(LocalDate startDate, LocalDate endDate, LeaveTypeEnum leaveType, String reason, LeaveStatusEnum status,
             Employee employee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reason = reason;
        this.status = status;
        this.employee = employee;
    }
    
    

}
