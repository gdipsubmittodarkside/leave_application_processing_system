package sg.nus.iss.team2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="user1")
//cos 'user' is h2 database keyword,
// cannot use user as table name
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

    @NotBlank(message = "enter your username")
    @Column(name="username")
    private String username;

    @NotBlank(message = "enter your password")
    @Column(name="password")
    private String password;

    @Column(name="join_date")
    private LocalDate joinDate;

    @Column(name="status")
    private String status;

    @Column(name = "manager_id")
    private Integer managerId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Leave> leaves;

    @OneToMany(mappedBy = "user")
    private List<CompensationRequest> compensationRequests;

    @OneToOne
    @JoinColumn(name = "leave_balance_id")
    private LeaveBalance leaveBalance;


}
