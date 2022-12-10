package sg.nus.iss.team2.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="leave_balance")
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalance {

    @Id
    @Column(name="leave_balance_id")
    private Integer leaveBalanceId;

    @OneToOne(mappedBy = "leaveBalance")
    private User user;

    @Column(name="balance_annual_leave_days")
    private Integer balanceAnnualLeaveDays;

    @Column(name="balance_medical_leave_days")
    private Integer balanceMedicalLeaveDays;


    @Column(name="balance_compensation_leave_days")
    private Double balanceCompensationLeaveDays;

}
