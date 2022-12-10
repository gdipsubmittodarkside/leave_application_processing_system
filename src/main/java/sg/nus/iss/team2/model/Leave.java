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
    private Integer leaveId;


    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="leave_type")
    private String leaveType;

    @Column(name="reason")
    private String reason;
    @Column(name="status")
    private String status;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


}
