import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import AddEmployee from './components/AddEmployee';
import EmployeeList from './components/EmployeeList';
import Navbar from './components/Navbar';
import UpdateEmployee from './components/UpdateEmployee';
import RegistrationForm from './components/registration/Register';

function App() {
  return (
    <>
    <BrowserRouter>
      <Navbar/>
        <Routes>
          <Route path="/*" element={<RegistrationForm />}></Route>
          <Route index element={<EmployeeList/>}/>
          <Route path="/" element={<EmployeeList/>}></Route>
          <Route path="/employeeList" element={<EmployeeList />}/>
          <Route path="/addEmployee" element={<AddEmployee/>}></Route>
          <Route path="/editEmployee/:employeeId" element={<UpdateEmployee/>}></Route>
        </Routes>
      
    </BrowserRouter>
    </>
  );
}

export default App;
