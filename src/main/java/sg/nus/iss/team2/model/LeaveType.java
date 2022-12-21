package sg.nus.iss.team2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LeaveType")
public class LeaveType {
    
    @Id
    @Column(name = "leave_type")
    private String leaveTypeName;

    @Column(name = "leave_period")
    private int leavePeriod;

    @Column(name = "leave_description")
    private String description;
    
}
