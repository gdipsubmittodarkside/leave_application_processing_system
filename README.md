# leave_application_processing_system

Instruction for Running Leave Application Processing System -Step by Step
1. Create Database

> Go to HeidiSQL (or) MySQL Workbench and log in

> create empty database in MySQL named - “db_team2”

2. Run Spring Boot Main App
> Open the project in VS Code or Spring Tool Suite

> Go to application.properties change username  and password to MySQL database’s connection username and password

> Run Leave_application_processing_system spring boot app

> Open browser http://localhost:8080/

> To see admin functions please log in with below username and password (caps-sensitive): 

username = admin,password = a123

> To see manager  functions please log in with below username and password (caps-sensitive): 

username = Kaung,password = a123

> To see staff functions please log in with below username and password (caps-sensitive): 

username = pearl, password = a123

3. Run React App

> Run Leave_application_processing_system-react-0.0.2 

> Open terminal and enter

npm install
npm start
Running on http://localhost:3000/


