package sg.nus.iss.team2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private String status;

    @Column(name = "manager_id")
    private Integer managerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaves;

    @OneToMany(mappedBy = "employee")
    private List<CompensationRequest> compensationRequests;

    @OneToOne
    @JoinColumn(name = "leave_balance_id")
    private LeaveBalance leaveBalance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Employee(String name, String status, Integer managerId) {
        this.name = name;
        this.status = status;
        this.managerId = managerId;
    }

    

    
}
