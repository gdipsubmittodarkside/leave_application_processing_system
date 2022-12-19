package sg.nus.iss.team2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    private Long employeeId;
    private String name;
    private String status;
    private Integer ManagerId;
}
