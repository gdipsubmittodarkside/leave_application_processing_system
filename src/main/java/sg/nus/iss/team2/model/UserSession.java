package sg.nus.iss.team2.model;

import java.util.List;

public class UserSession {
  private User user;
  private Employee employee;
  private List<Employee> subordinates;
  
  public UserSession() {}
  
  public UserSession(User user, Employee employee, List<Employee> subordinates) {
    this.user = user;
    this.employee = employee;
    this.subordinates = subordinates;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public List<Employee> getSubordinates() {
    return subordinates;
  }

  public void setSubordinates(List<Employee> subordinates) {
    this.subordinates = subordinates;
  }

}
  
  