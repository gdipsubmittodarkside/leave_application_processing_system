package nus.iss.team2.ADProjectTECHS.Controller.DeveloperController;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.iss.team2.ADProjectTECHS.Service.PythonAPIService;

@Controller
@RequestMapping("/call_python")
public class DeveloperController {

    public static List<String> jobList = Arrays.asList("SQL DEVELOPER", "SOFTWARE DEVELOPER",
    "DATA ENGINEER", "DATA ARCHITECT", "FULL STACK DEVELOPER", 
    "TECHNICAL CONSULTANT", "IT PROJECT EXECUTIVE", "Android DEVELOPER","UI UX DESIGNER", 
    "IOS DEVELOPER", "DEVOPS ENGINEER", "CLOUD SOLUTIONS ARCHITECT","FRONTEND ENGINEER",
    "ARTIFICIAL INTELLIGENCE ENGINEER","COMPUTER VISION ENGINEER", "SYSTEM ANALYST",
    "JAVA DEVELOPER", "DOT NET DEVELOPER");

    public static List<String> skillList = Arrays.asList("HTML", "JavaScript","SQL","C plus plus","Visual Basic",
    "CSS","Spring boot framework",	"Angular",	"Data Analysis Expressions","Azure Cloud","Java",
    "Talend","Azure Data Lake","Devpos","ReactJS","Amazon EC2","Tableau","C sharp", "Docker","Kubernetes",
    "Kotlin","vue.js","Redis","Spark Framework","Grafana","OAuth security framework","Flutter" ,"Figma",
    "Swift","Perl","XML","Bash",	"Python","MongoDB", "VB .NET", "J2ee","Terraform","Ruby","Groovy","Ubuntu",
    "Debian","Git","PHP","ASP.NET","Redux","Express","Bootstrap","OPENAPI","Linux",	"PostgreSQL","Ruby on Rails",
    "Flask","Django","Objective C","Nginx","jQuery","Apache","Node.js","Redshift","Snowflake","Laravel","Emberjs",
    "Backbonejs","Matlab","TypeScript","Scala","Rust","Go","Android Studio","Neural Network Models",
    "Machine Learning","Amazon S3","AWS Aurora", "Amazon DynamoDB",	"Amazon RDB","Amazon Lambda","Amazon VPC",
    "Natural Language Processing","HQL","PyTorch","Tensorflow",	"MindSpore","Drupal","Ether.js","Unity","FastAPI");

    public static List<String> skillDescList = Arrays.asList("HTML (HyperText Markup Language) is a standard markup language for web page creation. It allows the creation and structure of sections, paragraphs, and links using HTML elements (the building blocks of a web page) such as tags and attributes.",
    "JavaScript is a scripting language that enables you to create dynamically updating content, control multimedia, animate images, and pretty much everything else.",
    "SQL (Structured Query Language) is a popular query language designed for managing data held in databases. It is frequently used in all types of applications as it integrates well with different programming languages.",
    "This multi-paradigm language builds on the original C language supports procedural, generic, and object-oriented programming, along with data abstraction. C++ is extremely fast and has become widely popular.",
    "Visual Basic",
   "Web developers use CSS to create responsive and accessible websites. CSS can make it easier for web developers to create websites that look good on all devices, including mobile phones and tablets.",
   "Spring Boot framework",	
   "Angular is a platform and framework for building single-page client applications using HTML and TypeScript. Angular is written in TypeScript. It implements core and optional functionality as a set of TypeScript libraries that you import into your applications.",
   "Data Analysis Expressions","Azure Cloud",
   "From server-side applications to Android mobile games, Java is a powerful code that any programmer would benefit from learning. If you want to focus on design and structure but don’t want to spend as much time on memory management, then Java may be the right language for you.",
   "Talend","Azure Data Lake",
   "DevOps is a set of practices, tools, and a cultural philosophy that automate and integrate the processes between software development and IT teams.",
   "ReactJS","Amazon EC2",
   "Tableau is an excellent data visualization and business intelligence tool used for reporting and analyzing vast volumes of data. It helps users create different charts, graphs, maps, dashboards, and stories for visualizing and analyzing data, to help in making business decisions.",
   "Made to resemble Java in many respects, C# is a language designed for developing apps on the Microsoft platform. It is flexible, powerful, and easy to learn and is often used in game and app development.",
   "Docker",
   "Kubernetes, also known as K8s, is an open-source system for automating deployment, scaling, and management of containerized applications.",
   "JetBrains developed Kotlin as a statically-typed, general-purpose programming language. It is interoperable with Java code with a concise syntax. ",
   "vue.js","Redis","Spark Framework","Grafana","OAuth security framework","Flutter" ,
   "Figma is a browser-based web design tool that lets teams of users collaborate on projects to build interactive user interface prototypes.  What makes it so attractive is the fact that it’s free to use.",
   "Learn all the main concepts of Swift programming and apply your newly gained knowledge to create your own, fully functioning iOS app!",
   "Perl is a powerful programming language with many features and applications. It is widely used in system administration, web development, network programming, and many other fields. ",
   "XML","Bash",
   "Python is one of the most widely used programming languages in the world. It is considered a multi-paradigm coding language, which means that it allows programmers to take different approaches to achieve their goals.",
   "MongoDB is the world’s most popular document database with powerful capabilities like full-text search, geospatial queries, data aggregation, and more", 
   "VB .NET", "J2ee","Terraform",
   "A dynamic, open source programming language with a focus on simplicity and productivity. It has an elegant syntax that is natural to read and easy to write.",
   "Groovy","Ubuntu",
   "Debian","Git",
   "PHP (Hypertext Preprocessor) is a widely-used open source general-purpose scripting language that is especially suited for web development and can be embedded into HTML.",
   "ASP Dot NET","Redux","Express","Bootstrap","OPENAPI",
   "Linux® is an open source (OS) operating system where it directly manages a system’s hardware and resources, like CPU, memory, and storage. The OS sits between applications and hardware and makes the connections between all of your software and the physical resources that do the work.",
   "PostgreSQL","Ruby on Rails",
   "Flask","Django",
   "Objective-C is the primary programming language you use when writing software for OS X and iOS. It’s a superset of the C programming language and provides object-oriented capabilities and a dynamic runtime.",
   "Nginx",
   "Learn all the core features of jQuery, including making your website more interactive, creating effects and animations, handling events, and more!",
   "Apache","Node.js","Redshift","Snowflake","Laravel","Emberjs",
   "Backbonejs",	
   "MathWorks developed this programming language in the late 1970s for engineers and scientists as a tool to establish a numeric computing environment. The typical language usage includes data analysis, algorithm modeling, and scientific computations.",
   "TypeScript is used to create JavaScript-based projects with typing in both client-side and server-side development, making useful for catching errors and preventing systemic issues.",
   "Created in 2004, Scala is an object-oriented and functional programming language designed to address the disadvantages faced by users coding with Java.",
   "Rust is a compiled programming language developed in 2010 that is general purpose and focuses on memory safety and reliability.",
   "Go (sometimes called Golang) is one of the most popular and powerful open-source programming languages today. Developed by Google, it’s used on servers, web development, and even command-line interfaces. Let’s Go!",
   "Android Studio","Neural Network Models",
   "Learn how to create those intelligent recommendation systems that power our daily lives. Welcome to Machine Learning.",
   "Amazon S3","AWS Aurora", "Amazon DynamoDB",	"Amazon RDB","Amazon Lambda","Amazon VPC",
   "Natural Language Processing","HQL","PyTorch",
   "TensorFlow is a Python-friendly open source library for numerical computation that makes machine learning and developing neural networks faster and easier.",
   "MindSpore","Drupal","Ether.js","Unity","FastAPI");

   public static List<String> skillURLList = Arrays.asList("ok-plXXHlWw", "UU-GebNqdbg","27axs9dO7AE","MNeX4EGtR5Y","XXX Visual Basic",
   "OEV8gMkCHXQ","XXX Spring boot framework",	"Ata9cSC2WpM",	"XXX Data Analysis Expressions","XXX Azure Cloud","l9AzO1FMgM8",
   "XXX Talend","XXX Azure Data Lake","scEDHsr3APg","Tn6-PIqc4UM","XXX Amazon EC2","7Jl-RwkzqQ4","ravLFzIguCM", "Gjnup-PuquQ","PziYflu8cB8",
   "xT8oP0wy-A0","nhBVL41-_Cw","G1rOthIU-uo","XXX Spark Framework","XXX Grafana","Cx2dkpBxst8 OAuth security framework","Flutter" ,"Cx2dkpBxst8",
   "nAchMctX4YA","74_7LrRe5DI","XXX XML","I4EWvMFj37g", "x7X9w_GIm1s","-bt_y4Loofg", "XXX VB .NET", "XXX J2ee","tomUWcQ0P3k","XXX Ruby","XXX Groovy","XXX Ubuntu",
   "XXX Debian","XXX Git","a7_WFUlFS94","XXX ASP DOT NET","shA5Xwe8_4","XXX Express","XXX Bootstrap","XXX OPENAPI","rrB13utjYV4", "XXX PostgreSQL","XXX Ruby on Rails",
   "XXX Flask","XXX Django","VmrpCi1XCvg","XXX Nginx","XXX jQuery","XXX Apache","XXX Node.js","XXX Redshift","XXX Snowflake","XXX Laravel","XXX Emberjs",
   "XXX Backbonejs", "XXX Matlab","zQnBQ4tB3ZA","XXX Scala","5C_HPTJg5ek","446E-r0rXHI","XXX Android Studio","XXX Neural Network Models",
   "PeMlggyqz0Y","XXX Amazon S3","XXX AWS Aurora", "XXX Amazon DynamoDB",	"XXX Amazon RDB","XXX Amazon Lambda","XXX Amazon VPC",
   "XXX Natural Language Processing","XXX HQL","XXX PyTorch","i8NETqtGHms", "XXX MindSpore","XXX Drupal","XXX Ether.js","iqlH4okiQqg","XXX FastAPI");

    @Autowired
    private PythonAPIService pythonAPIService;

    @GetMapping(value={"/getAllJobs/{password}"}) //password = not set yet put whatever u want
    public String pythonCallAllJobs(@PathVariable String password, Model model){
        String result = "Failed Update";
        
        for(String job: jobList){
            try{
                String keyword = job.replace(" ","_");
                result = pythonAPIService.getAPIJobData(keyword);
            }catch(Exception e){
                System.out.println(e);
                result = "Failed Update";
            }
        }
        model.addAttribute("result", result);
        System.out.println(result);
        return "DBResult";

    }

    @GetMapping(value={"/getAllCourses/{password}"}) //password = not set yet put whatever u want
    public String pythonCallAllCourses(@PathVariable String password, Model model){
        String result = "";
        String skills = "";
        int count = 0;
        
        for(String skill: skillList){
            try{
                result = pythonAPIService.getAPICourseData(skill);
                skills += skill + ", ";
                count++;

            }catch(Exception e){
                System.out.println(e);
            }
        }
        result += ", Total Skill update = " + count + ", skills' name => " + skills;
        model.addAttribute("result", result);
        System.out.println(result);
        return "DBResult";
     }

    @GetMapping(value={"/getCourse/{keyword}"})
    public String pythonCallCourse(@PathVariable String keyword, Model model){
        String result = "";
        try{
            result = pythonAPIService.getAPICourseData(keyword);
            model.addAttribute("result", result);
            System.out.println(result);
            return "DBResult";
        }catch(Exception e){
            System.out.println(e);
        }
        model.addAttribute("result", result);
        return "DBResult";
    }

    @GetMapping(value={"/getJob/{keyword}"})
    public String pythonCallJob(@PathVariable String keyword, Model model){
        String result = "Failed Update";
        try{
            result = pythonAPIService.getAPIJobData(keyword);
            model.addAttribute("result", result);
            return "DBResult";
        }catch(Exception e){
            System.out.println(e);
        }
        model.addAttribute("result", result);
        return "DBResult";
    }

    // @GetMapping(value={"/getAllCourses/{password}"}) //password = iamfromteam2
    // public String pythonCallAllCourses(@PathVariable String password, Model model){
    //     String result = "";
        
    //     try{
    //         for(String skill: skillList){
    //             result = pythonAPIService.getAPICourseData(skill);
    //         }
    //         model.addAttribute("result", result);
    //         System.out.println(result);
    //         return "DBResult";
    //     }catch(Exception e){
    //         System.out.println(e);
    //         result = "Failed Update";
    //     }

        
        
        
    //     model.addAttribute("result", result);
    //     return "DBResult";
    // }

    // @GetMapping(value={"/getAllJobs/{password}"}) //password = iamfromteam2
    // public String pythonCallAllJobs(@PathVariable String password, Model model){
    //     String result = "Failed Update";
    //     try{
    //         result = pythonAPIService.getAllAPIJobData(password);
    //         return "DBResult";
    //     }catch(Exception e){
    //         System.out.println(e);
    //     }
    //     model.addAttribute("result", result);
    //     return "DBResult";

    // }



}
