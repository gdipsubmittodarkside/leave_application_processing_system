package sg.nus.iss.team2.model;

import java.time.LocalDate;

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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leave_request") // to make SQL able to create table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

    @Id
    @Column(name = "leave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @Column(name = "leave_type", columnDefinition = "ENUM('ANNUAL', 'MEDICAL', 'COMPENSATION')")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum leaveType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status", columnDefinition = "ENUM('APPLIED', 'CANCELLED', 'APPROVED', 'REJECTED', 'UPDATED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
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

    @Override
    public String toString() {
        return "Leave [leaveId=" + leaveId + ", startDate=" + startDate + ", endDate=" + endDate + ", leaveType="
                + leaveType + ", reason=" + reason + ", status=" + status + ", comment=" + comment + ", employee="
                + employee + "]";
    }

}
