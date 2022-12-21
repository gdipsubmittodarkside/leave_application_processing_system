import React from 'react'
import { useNavigate } from 'react-router-dom';

const Employee = ({employee,deleteEmployee}) => {

    const navigate = useNavigate();
    const editEmployee = (e,employeeId) =>{
        e.preventDefault();
        navigate(`/editEmployee/${employeeId}`)

    };

  return (
    <tr key={employee.employeeId}>
                        <td className='text-left px-6 py-4 whitespace-nowrap' >
                            <div className='text-sm text-gray-500'>{employee.employeeId }</div></td>
                        <td className='text-left px-6 py-4 whitespace-nowrap' >
                            <div className='text-sm text-gray-500'>{employee.name }</div></td>
                        <td className='text-left px-6 py-4 whitespace-nowrap' >
                            <div className='text-sm text-gray-500'>{employee.status }</div></td>
                        <td className='text-left px-6 py-4 whitespace-nowrap' >
                            <div className='text-sm text-gray-500'>{employee.managerId }</div></td>
                        <td className='text-right px-6 py-4 whitespace-nowrap font-medium text-sm' >
                            <a 
                            onClick={(e,employeeId)=>editEmployee(e,employee.employeeId)}
                            className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>
                                Edit</a>
                            <a 
                            onClick={(e,employeeId)=>deleteEmployee(e,employee.employeeId)}
                             className='text-indigo-600 hover:text-indigo-800 hover:cursor-pointer'>
                                Delete</a>
                            </td>
                    </tr>
  );
};

export default Employee