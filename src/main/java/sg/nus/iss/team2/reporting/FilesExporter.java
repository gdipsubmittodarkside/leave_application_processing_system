package sg.nus.iss.team2.reporting;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import sg.nus.iss.team2.model.CompensationRequest;
import sg.nus.iss.team2.model.Employee;
import sg.nus.iss.team2.model.Leave;
import sg.nus.iss.team2.model.LeaveType;
import sg.nus.iss.team2.model.User;

public class FilesExporter {
        public void exportEmployeeToCSV(List<Employee> ListEmployee, HttpServletResponse response)
                        throws IOException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStamp = dateFormat.format(new Date());
                String fileName = "Employees_" + timeStamp + ".csv";

                response.setContentType("text/csv");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);

                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = { "EMPLOYEEID", "NAME", "STATUS", "MANAGERID" };
                String[] fieldMapping = { "employeeId",
                                "name", "status", "managerId" };

                csvWriter.writeHeader(csvHeader);
                for (Employee e : ListEmployee) {
                        csvWriter.write(e, fieldMapping);

                }
                csvWriter.close();
        }

        public void exportUserToCSV(List<User> ListUser, HttpServletResponse response)
                        throws IOException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStamp = dateFormat.format(new Date());
                String fileName = "Users_" + timeStamp + ".csv";

                response.setContentType("text/csv");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);

                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = { "USERID", "USERNAME", "PASSWORD", "JOINDATE", "EMPLOYEEID" };
                String[] fieldMapping = { "userId",
                                "username", "password", "joinDate", "employee" };

                csvWriter.writeHeader(csvHeader);
                for (User e : ListUser) {
                        csvWriter.write(e, fieldMapping);

                }
                csvWriter.close();
        }

        public void exportLeaveTypeToCSV(List<LeaveType> ListLeaveType, HttpServletResponse response)
                        throws IOException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStamp = dateFormat.format(new Date());
                String fileName = "LeaveType_" + timeStamp + ".csv";

                response.setContentType("text/csv");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);

                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = { "LEAVETYPE_NAME", "LEAVE_PERIOD", "DESCRIPTION" };
                String[] fieldMapping = { "leaveTypeName",
                                "leavePeriod", "description" };

                csvWriter.writeHeader(csvHeader);
                for (LeaveType e : ListLeaveType) {
                        csvWriter.write(e, fieldMapping);

                }
                csvWriter.close();
        }

        public void exportCompensationHisToCSV(List<CompensationRequest> CompensationHisType,
                        HttpServletResponse response)
                        throws IOException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStamp = dateFormat.format(new Date());
                String fileName = "CompensationHis_" + timeStamp + ".csv";

                response.setContentType("text/csv");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);

                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = { "Compensation_Id", "employee.name", "OT_START_DATE", "OT_END_DATE",
                                "DESCRIPTION",
                                "STATUS", "MANAGER_COMMENT" };

                String[] fieldMapping = { "compensationLeaveId", "employee",
                                "OTstartTime", "OTendTime", "description", "status", "comment" };

                csvWriter.writeHeader(csvHeader);
                for (CompensationRequest e : CompensationHisType) {
                        csvWriter.write(e, fieldMapping);

                }
                csvWriter.close();
        }

        public void exportLeaveHisToCSV(List<Leave> leaveHisType,
                        HttpServletResponse response)
                        throws IOException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStamp = dateFormat.format(new Date());
                String fileName = "leaveHis_" + timeStamp + ".csv";

                response.setContentType("text/csv");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=" + fileName;
                response.setHeader(headerKey, headerValue);

                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = { "LEAVE_ID", "START_DATE", "END_DATE", "LEACE_TYPE", "REASON",
                                "STATUS", "COMMENT", "EMPLOYEE" };

                String[] fieldMapping = { "leaveId", "startDate",
                                "endDate", "leaveType", "reason", "status", "comment", "employee" };

                csvWriter.writeHeader(csvHeader);
                for (Leave e : leaveHisType) {
                        csvWriter.write(e, fieldMapping);

                }
                csvWriter.close();
        }

}
