package sg.nus.iss.team2;

import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveBalance;
import sg.nus.iss.team2.model.User;
import sg.nus.iss.team2.repository.LeaveBalanceRepository;
import sg.nus.iss.team2.repository.LeaveRepository;
import sg.nus.iss.team2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class LeavingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeavingSystemApplication.class);
    }
    @Bean
    CommandLineRunner loadData(
            UserRepository userRepository,
            LeaveRepository leaveRepository,
            LeaveBalanceRepository leaveBalanceRepository
    ){
        return(args) ->{
            User staffUser = new User();
            staffUser.setUserId(1);
            staffUser.setName("Staff Lee");
            staffUser.setUsername("s123");
            staffUser.setPassword("s123");
            staffUser.setRole("staff");
            staffUser.setJoinDate(LocalDate.parse("2000-01-01"));
            staffUser.setStatus("existing");
            staffUser.setManagerId(2);


            User managerUser = new User();
            managerUser.setUserId(2);
            managerUser.setName("Manager Lee");
            managerUser.setUsername("m123");
            managerUser.setPassword("m123");
            managerUser.setRole("manager");
            managerUser.setJoinDate(LocalDate.parse("2000-02-02"));
            managerUser.setStatus("existing");
            managerUser.setManagerId(3);


            User adminUser = new User();
            adminUser.setUserId(3);
            adminUser.setName("Admin Lee");
            adminUser.setUsername("a123");
            adminUser.setPassword("a123");
            adminUser.setRole("admin");
            adminUser.setJoinDate(LocalDate.parse("2000-03-03"));
            adminUser.setStatus("existing");
            userRepository.saveAllAndFlush(Arrays.asList(staffUser,managerUser,adminUser));

            Leave sLeave1 = new Leave();
            sLeave1.setLeaveId(1);
            sLeave1.setUser(staffUser);
            sLeave1.setStartDate(LocalDate.parse("2022-02-10"));
            sLeave1.setEndDate(LocalDate.parse("2022-02-15"));
            sLeave1.setStatus("applied");
            sLeave1.setReason("reason: two");
            sLeave1.setLeaveType("medical");

            Leave sLeave2 = new Leave();
            sLeave2.setLeaveId(2);
            sLeave2.setUser(staffUser);
            sLeave2.setStartDate(LocalDate.parse("2022-01-01"));
            sLeave2.setEndDate(LocalDate.parse("2022-01-07"));
            sLeave2.setStatus("canceled");
            sLeave2.setReason("reason: one");
            sLeave2.setLeaveType("annual");

            Leave mLeave1 = new Leave();
            mLeave1.setLeaveId(3);
            mLeave1.setUser(managerUser);
            mLeave1.setStartDate(LocalDate.parse("2022-01-01"));
            mLeave1.setEndDate(LocalDate.parse("2022-01-07"));
            mLeave1.setStatus("applied");
            mLeave1.setReason("reason: seven");
            mLeave1.setLeaveType("annual");

            Leave mLeave2 = new Leave();
            mLeave2.setLeaveId(4);
            mLeave2.setUser(managerUser);
            mLeave2.setStartDate(LocalDate.parse("2022-01-01"));
            mLeave2.setEndDate(LocalDate.parse("2022-01-07"));
            mLeave2.setStatus("applied");
            mLeave2.setReason("reason: five");
            mLeave2.setLeaveType("annual");

            Leave mLeave3 = new Leave();
            mLeave3.setLeaveId(5);
            mLeave3.setUser(managerUser);
            mLeave3.setStartDate(LocalDate.parse("2022-01-01"));
            mLeave3.setEndDate(LocalDate.parse("2022-01-07"));
            mLeave3.setStatus("applied");
            mLeave3.setReason("reason: four");
            mLeave3.setLeaveType("compensation");

            LeaveBalance sLeaveBalance = new LeaveBalance();
            sLeaveBalance.setLeaveBalanceId(1);
            sLeaveBalance.setUser(staffUser);
            sLeaveBalance.setBalanceAnnualLeaveDays(20);
            sLeaveBalance.setBalanceMedicalLeaveDays(100);
            sLeaveBalance.setBalanceCompensationLeaveDays(20.5);

            LeaveBalance mLeaveBalance = new LeaveBalance();
            mLeaveBalance.setLeaveBalanceId(2);
            mLeaveBalance.setUser(managerUser);
            mLeaveBalance.setBalanceAnnualLeaveDays(25);
            mLeaveBalance.setBalanceMedicalLeaveDays(100);
            mLeaveBalance.setBalanceCompensationLeaveDays(10.5);


            staffUser.setLeaves(Arrays.asList(sLeave1,sLeave2));
            staffUser.setLeaveBalance(sLeaveBalance);

            managerUser.setLeaves(Arrays.asList(mLeave1,mLeave2,mLeave3));
            managerUser.setLeaveBalance(mLeaveBalance);

            leaveRepository.saveAllAndFlush(Arrays.asList(sLeave1,sLeave2,mLeave1,mLeave2,mLeave3));

            leaveBalanceRepository.saveAllAndFlush(Arrays.asList(sLeaveBalance,mLeaveBalance));

            userRepository.saveAllAndFlush(Arrays.asList(staffUser,managerUser,adminUser));


        };

    }

}



