package sg.nus.iss.team2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.nus.iss.team2.configuration.PublicHolidayApi;
import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.LeaveStatusEnum;
import sg.nus.iss.team2.model.LeaveType;
import sg.nus.iss.team2.model.LeaveTypeEnum;
import sg.nus.iss.team2.model.Role;
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.reporting.FilesExporter;
import sg.nus.iss.team2.repository.CompensationRequestRepository;
import sg.nus.iss.team2.repository.EmployeeRepository;
import sg.nus.iss.team2.repository.LeaveBalanceRepository;
import sg.nus.iss.team2.repository.LeaveRepository;
import sg.nus.iss.team2.repository.LeaveTypeRepository;
import sg.nus.iss.team2.repository.RoleRepository;
import sg.nus.iss.team2.repository.UserRepository;
import sg.nus.iss.team2.service.PublicHolidayService;

@SpringBootApplication
public class LeavingSystemApplication {

        public static void main(String[] args) {
                SpringApplication.run(LeavingSystemApplication.class);
        }

        @Autowired
        private PublicHolidayApi api;
        @Autowired
        private PublicHolidayService pubService;

        @Bean
        public FilesExporter filesExporter() {
                return new FilesExporter();
        }

        @Bean
        CommandLineRunner loadData(
                        UserRepository userRepository,
                        LeaveRepository leaveRepository,
                        LeaveBalanceRepository leaveBalanceRepository,
                        CompensationRequestRepository compensationRequestRepository,
                        RoleRepository roleRepository,
                        LeaveTypeRepository leaveTypeRepo,
                        EmployeeRepository empRepo) {
                return (args) -> {

                        Employee emp1 = new Employee("Kaung", "existing", 1);
                        Employee emp2 = new Employee("KaungPon", "existing", 1);
                        Employee emp3 = new Employee("KaungJik", "existing", 2);
                        Employee emp4 = new Employee("KaungLwn", "existing", 1);
                        Employee emp5 = new Employee("KaungOm", "existing", 2);
                        Employee emp6 = new Employee("KaungIn", "existing", 1);
                        Employee emp7 = new Employee("KaungYun", "existing", 2);
                        Employee emp8 = new Employee("KaungTyn", "existing", 2);
                        Employee emp9 = new Employee("KaungErn", "existing", 1);
                        Employee emp10 = new Employee("KaungWee", "existing", 2);
                        Employee emp11 = new Employee("KaungPop", "existing", 2);
                        Employee emp12 = new Employee("KaungList", "existing", 1);

                        User staff1 = new User("Kaung", "a123", LocalDate.now());
                        User staff2 = new User("Yun", "a123", LocalDate.now().plusDays(1));
                        User staff3 = new User("Joey", "a123", LocalDate.now().plusDays(2));
                        User staff4 = new User("YIKAI", "a123", LocalDate.now().minusMonths(2));
                        User staff5 = new User("pearl", "a123", LocalDate.now().minusDays(7));
                        User staff6 = new User("tin", "a123", LocalDate.now().minusDays(3));
                        User staff7 = new User("cherwah", "a123", LocalDate.now().minusWeeks(5));
                        User staff8 = new User("YK", "a123", LocalDate.now().minusYears(2));
                        User staff9 = new User("esther", "a123", LocalDate.now().minusDays(20));
                        User staff10 = new User("admin", "a123", LocalDate.now());
                        User staff11 = new User("adminB", "a123", LocalDate.now().minusWeeks(4));
                        User staff12 = new User("adminC", "a123", LocalDate.now());

                        Role admin = new Role("admin");
                        Role staff = new Role("staff");
                        Role manager = new Role("manager");

                        emp1.setUser(staff1);
                        emp2.setUser(staff2);
                        emp3.setUser(staff3);
                        emp4.setUser(staff4);
                        emp5.setUser(staff5);
                        emp6.setUser(staff6);
                        emp7.setUser(staff7);
                        emp8.setUser(staff8);
                        emp9.setUser(staff9);
                        emp10.setUser(staff10);
                        emp11.setUser(staff11);
                        emp12.setUser(staff12);

                        staff1.setRoles(Arrays.asList(manager, staff));
                        staff2.setRoles(Arrays.asList(manager, staff));
                        staff3.setRoles(Arrays.asList(staff));
                        staff4.setRoles(Arrays.asList(staff));
                        staff5.setRoles(Arrays.asList(staff));
                        staff6.setRoles(Arrays.asList(staff));
                        staff7.setRoles(Arrays.asList(staff));
                        staff8.setRoles(Arrays.asList(staff));
                        staff9.setRoles(Arrays.asList(staff));
                        staff10.setRoles(Arrays.asList(admin, staff));
                        staff11.setRoles(Arrays.asList(admin, staff));
                        staff12.setRoles(Arrays.asList(admin, staff));

                        Leave leave1 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.ANNUAL, "Family issue", LeaveStatusEnum.APPLIED, emp1);
                        Leave leave2 = new Leave(LocalDate.parse("2022-10-10"),
                                        LocalDate.parse("2022-10-15"),
                                        LeaveTypeEnum.COMPENSATION, "Wedding errands", LeaveStatusEnum.APPROVED, emp2);
                        Leave leave3 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-20"),
                                        LeaveTypeEnum.MEDICAL, "Running errands", LeaveStatusEnum.APPLIED, emp3);
                        Leave leave4 = new Leave(LocalDate.parse("2022-11-11"),
                                        LocalDate.parse("2022-11-20"),
                                        LeaveTypeEnum.ANNUAL, "Personal issue", LeaveStatusEnum.UPDATED, emp4);
                        Leave leave5 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.MEDICAL, "Family matters", LeaveStatusEnum.CANCELLED, emp5);
                        Leave leave6 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.ANNUAL, "Vacation", LeaveStatusEnum.APPLIED, emp6);
                        Leave leave7 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.ANNUAL, "Me time", LeaveStatusEnum.APPLIED, emp7);

                        Leave leave8 = new Leave(LocalDate.parse("2022-08-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.ANNUAL, "Attend wedding", LeaveStatusEnum.APPLIED, emp8);
                        Leave leave9 = new Leave(LocalDate.parse("2022-08-10"),
                                        LocalDate.parse("2022-08-11"),
                                        LeaveTypeEnum.ANNUAL, "Holiday", LeaveStatusEnum.APPLIED, emp9);
                        Leave leave10 = new Leave(LocalDate.parse("2022-12-30"),
                                        LocalDate.parse("2022-12-31"),
                                        LeaveTypeEnum.ANNUAL, "Overseas trip", LeaveStatusEnum.APPLIED, emp10);
                        Leave leave11 = new Leave(LocalDate.parse("2023-01-16"),
                                        LocalDate.parse("2023-02-10"),
                                        LeaveTypeEnum.ANNUAL, "Family gathering", LeaveStatusEnum.APPLIED, emp1);
                        Leave leave12 = new Leave(LocalDate.parse("2022-10-10"),
                                        LocalDate.parse("2022-10-11"),
                                        LeaveTypeEnum.ANNUAL, "Personal matters", LeaveStatusEnum.APPLIED, emp1);
                        Leave leave13 = new Leave(LocalDate.parse("2022-09-10"),
                                        LocalDate.parse("2022-09-11"),
                                        LeaveTypeEnum.ANNUAL, "JUST FOR HOT POT", LeaveStatusEnum.APPLIED, emp1);

                        LeaveType annual_admin = new LeaveType("annual_admin", 14, "Admin annual leave");
                        LeaveType medical = new LeaveType("medical", 60, "Medical certified leave");

                        LeaveType annual_staff = new LeaveType("annual_staff", 18, "Staff annual leave");

                        LeaveBalance staff_balance_1 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance admin_balance_1 = new LeaveBalance(annual_admin.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_2 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance admin_balance_2 = new LeaveBalance(annual_admin.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_3 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance admin_balance_3 = new LeaveBalance(annual_admin.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_4 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);

                        LeaveBalance staff_balance_5 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_6 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_7 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_8 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);
                        LeaveBalance staff_balance_9 = new LeaveBalance(annual_staff.getLeavePeriod(),
                                        medical.getLeavePeriod(), 0.0);

                        emp1.setLeaveBalance(staff_balance_1);
                        emp2.setLeaveBalance(staff_balance_2);
                        emp3.setLeaveBalance(staff_balance_3);
                        emp4.setLeaveBalance(staff_balance_4);
                        emp5.setLeaveBalance(staff_balance_5);
                        emp6.setLeaveBalance(staff_balance_6);
                        emp7.setLeaveBalance(staff_balance_7);
                        emp8.setLeaveBalance(staff_balance_8);
                        emp9.setLeaveBalance(staff_balance_9);
                        emp10.setLeaveBalance(admin_balance_1);
                        emp11.setLeaveBalance(admin_balance_2);
                        emp12.setLeaveBalance(admin_balance_3);

                        CompensationRequest cr1 = new CompensationRequest(LocalDateTime.parse("2022-12-12T17:30:00"),
                                        LocalDateTime.parse("2022-12-12T21:30:00"), LeaveStatusEnum.APPLIED, emp2);
                        CompensationRequest cr2 = new CompensationRequest(LocalDateTime.parse("2022-12-13T16:30:00"),
                                        LocalDateTime.parse("2022-12-13T22:30:00"), LeaveStatusEnum.APPROVED, emp4);
                        CompensationRequest cr3 = new CompensationRequest(LocalDateTime.parse("2022-12-14T14:30:00"),

                                        LocalDateTime.parse("2022-12-14T20:30:00"), LeaveStatusEnum.UPDATED, emp12);
                        CompensationRequest cr4 = new CompensationRequest(LocalDateTime.parse("2022-12-15T12:30:00"),
                                        LocalDateTime.parse("2022-12-15T20:45:00"), LeaveStatusEnum.APPROVED, emp4);
                        CompensationRequest cr5 = new CompensationRequest(LocalDateTime.parse("2022-12-16T18:30:00"),
                                        LocalDateTime.parse("2022-12-16T20:30:00"), LeaveStatusEnum.UPDATED, emp12);
                        emp1.setCompensationRequests(Arrays.asList(cr1, cr2, cr3));
                        emp2.setCompensationRequests(Arrays.asList(cr2, cr4));
                        emp3.setCompensationRequests(Arrays.asList(cr3, cr5));
                        emp4.setCompensationRequests(Arrays.asList(cr1));
                        emp5.setCompensationRequests(Arrays.asList(cr2, cr3));
                        emp11.setCompensationRequests(Arrays.asList(cr3, cr5));
                        emp12.setCompensationRequests(Arrays.asList(cr3));

                        leaveBalanceRepository.saveAllAndFlush(Arrays.asList(staff_balance_1,
                                        staff_balance_2,
                                        staff_balance_3,
                                        staff_balance_4,
                                        staff_balance_5,
                                        staff_balance_6,
                                        staff_balance_7,
                                        staff_balance_8,
                                        staff_balance_9,
                                        admin_balance_1,
                                        admin_balance_2,
                                        admin_balance_3));
                        roleRepository.saveAll(Arrays.asList(admin, staff, manager));
                        leaveTypeRepo.saveAllAndFlush(Arrays.asList(annual_admin, annual_staff, medical));
                        userRepository.saveAllAndFlush(Arrays.asList(staff1, staff2, staff3, staff4, staff5, staff6,
                                        staff7, staff8, staff9, staff10, staff11, staff12));
                        empRepo.saveAllAndFlush(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9,
                                        emp10, emp11, emp12));
                        compensationRequestRepository.saveAllAndFlush(Arrays.asList(cr1, cr2, cr3, cr4, cr5));
                        leaveRepository.saveAllAndFlush(
                                        Arrays.asList(leave1, leave2, leave3, leave4, leave5, leave6, leave7, leave8,
                                                        leave9, leave10, leave11, leave12, leave13));
                        pubService.saveAll(api.getPublicHoliday("2022"));
                        pubService.saveAll(api.getPublicHoliday("2023"));
                };
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
                return new WebMvcConfigurer() {
                        @Override
                        public void addCorsMappings(CorsRegistry registry) {
                                registry.addMapping("/**")
                                                .allowedMethods("*")
                                                .allowCredentials(true)
                                                .allowedHeaders("*")
                                                .allowedOrigins("http://localhost:3000");
                        }
                };
        }

}