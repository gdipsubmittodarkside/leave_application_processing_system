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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveBalanceId;

    @OneToOne(mappedBy = "leaveBalance")
    private Employee employee;

    @Column(name="balance_annual_leave_days")
    private Integer balanceAnnualLeaveDays;

    @Column(name="balance_medical_leave_days")
    private Integer balanceMedicalLeaveDays;


    @Column(name="balance_compensation_leave_days")
    private Double balanceCompensationLeaveDays;


    public LeaveBalance(Integer balanceAnnualLeaveDays, Integer balanceMedicalLeaveDays,
            Double balanceCompensationLeaveDays) {
        this.balanceAnnualLeaveDays = balanceAnnualLeaveDays;
        this.balanceMedicalLeaveDays = balanceMedicalLeaveDays;
        this.balanceCompensationLeaveDays = balanceCompensationLeaveDays;
    }

}
