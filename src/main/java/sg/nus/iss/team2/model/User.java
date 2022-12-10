package sg.nus.iss.team2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="user1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    private Integer userId;

    @Column(name="name")
    private String name;
    @Column(name="role")
    private String role;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    @Column(name="join_date")
    private LocalDate joinDate;

    @Column(name="status")
    private String status;

    @Column(name = "manager_id")
    private Integer managerId;

    @OneToMany(mappedBy = "user")
    private List<Leave> leaves;

    @OneToOne
    @JoinColumn(name = "leave_balance_id")
    private LeaveBalance leaveBalance;


}
