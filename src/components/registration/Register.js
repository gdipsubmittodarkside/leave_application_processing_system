import React, { useState } from 'react';
import axios from 'axios';

function RegistrationForm() {

 
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errMsg, setErrMsg] = useState("");
    const [sucMsg, setSucMsg] = useState("");
    const [roles, setRoles] = useState(["staff"]);
    const [checked, setChecked] = useState(false);
    let responseBody;

    const handleCheck = () => {
        setChecked(!checked)
        if(checked === true){
            setRoles(old => [...old, "manager"])
        } else {
            setRoles(["staff"])
        }
    }

    const baseURL = "http://localhost:8080"
    const REGISTER_URL = "http://localhost:8080/register";

    const handleSubmit = async(e) => {
        e.preventDefault();
        if (username==='' || password ==='') {
          setErrMsg("Invalid Entry");
          setSucMsg("")
          return;
        }
        try {
            const response = await axios.post(
              REGISTER_URL,
              JSON.stringify({ username, password , roles}),
              {
                headers: { "Content-Type": "application/json" },
                withCredentials: true,
              }
            ).then((response) => {responseBody = response.data});
            
            
            if(responseBody === "Success"){
              setErrMsg("")
              setUsername("");
              setPassword("");
              setSucMsg("Successfully")
            }else{
              setErrMsg("Username has been registred");
              setPassword("");
              setSucMsg("")
            }
            
          

          } catch (err) {
            if (!err?.response) {
              setErrMsg("No Server Response");
            } else if (err.response?.status === 409) {
              setErrMsg("Username Taken");
            } else {
              setErrMsg("Registration Failed");
            }
          }
    }

    
    return (
        <div className="container-fluid text-center mx-auto" style={{width:300}}>
            <h2 className='my-10'>Registration</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label >Username</label>
                    <input type="text" className="form-control" id="username" onChange={(e) => setUsername(e.target.value)} name="username" value={username} placeholder="Enter username" />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" id="password" onChange={(e) => setPassword(e.target.value)} name="password" value={password} placeholder="Password"  />
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="checkbox" id="inlineCheckbox1" checked disabled />
                        <label className="form-check-label" >Staff (default)</label>
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="checkbox" id="inlineCheckbox2" checked={checked} onChange={handleCheck} />
                        <label className="form-check-label">Manager</label>
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="checkbox" id="inlineCheckbox3" disabled />
                        <label className="form-check-label">Admin (disabled)</label>
                </div>
                
                <div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
                <div><i>{errMsg}</i></div>
                <div><i>{sucMsg}</i></div>
            </form>
        </div>
    )
}
export default RegistrationForm;