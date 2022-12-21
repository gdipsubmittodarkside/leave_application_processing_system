import React from 'react'
import { useNavigate } from 'react-router-dom'
import {useState,useEffect} from 'react';
import EmployeeService from '../Service/EmployeeService';
import Employee from './Employee';

const EmployeeList = () => {

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [employee, setEmployee] = useState(null);

    useEffect(() => {
      const fetchData = async () => {
        setLoading(true);
        try{
            const response = await EmployeeService.getEmployee();
            setEmployee(response.data);
        }catch(error){
            console.log(error);
        }
        setLoading(false);
      };
      fetchData();
    }, []);
    
    const deleteEmployee = (e,employeeId) =>{
        e.preventDefault();
        EmployeeService.deleteEmployee(employeeId)
        .then((res)=>{
            if(employee){
                setEmployee((prevElement) => {
                    return prevElement.filter((employee) => employee.employeeId !== employeeId);
                });
            }
        });

    };

  return (
    <div className='container mx-auto my-8'>
        <div className='h-12'>
            <button 
            onClick={() => navigate("/addEmployee")}
            className='rounded bg-slate-600 text-white px-6 py-2 font-semi'>Add Employee</button>

        </div>
        <div className='flex shadow border-b'>
            <table className='min-w-full'>
                <thead className='bg-gray-50'>
                    <tr>
                        <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>
                            EmployeeId</th>
                        <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>
                            Name</th>
                        <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>
                            Status</th>
                        <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>
                            ManagerID</th>
                        <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>
                            Actions</th>
                    </tr>
                </thead>
                {!loading &&(
                <tbody className='bg-white'>
                    {employee.map((employee) => (
                        <Employee employee={employee} 
                        deleteEmployee={deleteEmployee} 
                        key={employee.employeeId}></Employee>
                    ))}
                </tbody>
                )}

            </table>
        </div>
    </div>
  )
}

export default EmployeeList