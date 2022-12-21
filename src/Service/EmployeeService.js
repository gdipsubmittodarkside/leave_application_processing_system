import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employee"

class EmployeeService {
    saveEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL,employee);
    }

    getEmployee(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }

    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_API_BASE_URL+"/"+employeeId);
    }

    getEmployeeById(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL+"/"+employeeId);
    }

    updateEmployee(employee,employeeId){
        return axios.put(EMPLOYEE_API_BASE_URL+"/"+employeeId,employee);
    }

}

export default new EmployeeService();