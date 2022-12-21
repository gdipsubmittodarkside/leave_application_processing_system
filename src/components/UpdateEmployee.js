import React,{useState,useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../Service/EmployeeService';

const UpdateEmployee = () => {

    const {employeeId} = useParams();   
    const naviagte = useNavigate();
    const [employee, setEmployee] = useState({
        id:employeeId,
        name:"",
        status:"",
        managerId:"",
    })

    const handleChange = (e) =>{
        const value = e.target.value;
        setEmployee({...employee,[e.target.name]:value});
    }

    useEffect(() => {
      const fetchData = async () =>{
        try{
            const response =await EmployeeService.getEmployeeById(employeeId);
            setEmployee(response.data);
        }catch(error){
            console.log(error)
        }
      };
      fetchData();
    }, []);
    
    

    const updateEmployee = (e)=>{
        e.preventDefault();
        EmployeeService.updateEmployee(employee,employeeId)
        .then((response)=>{
            naviagte("/employeeList");
        }).catch((error) =>{
            console.log(error)});
    };
  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
        <div className="px-8 py-8">
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Update Employee</h1>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className='block text-gray-600 text-sm font-normal'>Name</label>
                <input type="text" 
                name="name"
                value={employee.name}
                onChange={(e)=>handleChange(e)}
                className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>

            <div className="items-center justify-center h-14 w-full my-4">
                <label className='block text-gray-600 text-sm font-normal'>Status</label>
                <input type="text" 
                name="status"
                value={employee.status}
                onChange={(e)=>handleChange(e)}
                className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>

            <div className="items-center justify-center h-14 w-full pt-4">
                ManagerID: <select className='border mt-2 px-2 py-2'
                name="managerId" value={employee.managerId}
                onChange={(e)=>handleChange(e)}>
                    <option selected value=""> Please Select Manager</option>
                    <option value="1">1. Kaung1</option>
                    <option value="2">2. Kaung2</option>
                    <option value="3">3. Kaung3</option>
                    <option value="4">4. Kaung4</option>
                </select>
            </div>
            <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
                <button 
                onClick={updateEmployee} 
                className='rounded  text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6  '>
                    Update</button>
                <button 
                onClick={()=>naviagte("/employeeList")}
                className='rounded  text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6  '>
                    Cancel</button>
            </div>
        </div>
    </div>
  )
}

export default UpdateEmployee