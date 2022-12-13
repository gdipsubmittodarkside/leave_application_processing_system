package sg.nus.iss.team2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="LAPS_User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "enter your username")
    @Column(name="username")
    private String username;

    @NotBlank(message = "enter your password")
    @Column(name="password")
    private String password;

    @Column(name="join_date")
    private LocalDate joinDate;

    @OneToOne(mappedBy = "user")
    private Employee employee;
 
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name="userrole",joinColumns = {
        @JoinColumn(name="user_id",referencedColumnName = "user_id")
    }, inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "role_id")})
    private List<Role> roles;

    public User(@NotBlank(message = "enter your username") String username,
            @NotBlank(message = "enter your password") String password, LocalDate joinDate) {
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
    }
    
}
