package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.Job;
import nus.iss.team2.ADProjectTECHS.Model.JobSkill;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Model.Data.CourseData;
import nus.iss.team2.ADProjectTECHS.Model.Data.JobData;
import nus.iss.team2.ADProjectTECHS.Service.CourseCrawledService;
import nus.iss.team2.ADProjectTECHS.Service.JobService;
import nus.iss.team2.ADProjectTECHS.Service.JobSkillService;
import nus.iss.team2.ADProjectTECHS.Service.PythonAPIService;
import nus.iss.team2.ADProjectTECHS.Service.SkillService;
import nus.iss.team2.ADProjectTECHS.Utility.utility;

@Component
public class PythonAPIServiceImpl implements PythonAPIService {
    private String baseURL = "http://localhost:8089//";
    private LocalDate todayDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> jobList = Arrays.asList("MS SQL DEVELOPER", "SOFTWARE DEVELOPER", 
        "IT TECHNICAL ANALYST", "DATA ENGINEER", "DATA ARCHITECT", "FULL STACK DEVELOPER", 
        "TECHNICAL CONSULTANT", "IT PROJECT EXECUTIVE", "MOBILE APP DEVELOPER","UIUX PRODUCT DESIGNER", 
        "IOS MOBILE APPLICATION DEVELOPER", "DEVOPS ENGINEER","FRONTEND ENGINEER",
        "ARTIFICIAL INTELLIGENCE ENGINEER","COMPUTER VISION ENGINEER");

    public static List<String> skillList = Arrays.asList("HTML", "JavaScript","SQL","C plus plus","Visual Basic",
        "CSS","Spring boot framework",	"Angular",	"Data Analysis Expressions","Azure Cloud","Java",
        "Talend","Azure Data Lake","Devpos","ReactJS","Amazon EC2","Tableau","C sharp", "Docker","Kubernetes",
        "Kotlin","vue.js","Redis","Spark Framework","Grafana","OAuth security framework","Flutter","C","Figma",
        "Swift","Perl","XML","Bash",	"Python","MongoDB", "VB .NET", "J2ee","Terraform","Ruby","Groovy","Ubuntu",
        "Debian","Git","PHP","ASP.NET","Redux","Express","Boostrap","OPENAPI","Linux",	"PostgreSQL","Ruby on Rails",
        "Flask","Django","Objective-C","Nginx","jQuery","Apache","Node.js","Redshift","Snowflake","Laravel","Emberjs",
        "Backbonejs",	"R","Matlab","TypeScript","Scala","Rust","Go","Android Studio","Neural Network Models",
        "Machine Learning","Amazon S3","AWS Aurora", "Amazon DynamoDB",	"Amazon RDB","Amazon Lambda","Amazon VPC",
        "Natural Language Processing","HQL","PyTorch","Tensorflow",	"MindSpore","Drupal","Ether.js","Unity","FastAPI");

    @Autowired
    SkillService skillService;

    @Autowired
    CourseCrawledService cwService;

    @Autowired
    JobService jobService;

    @Autowired
    JobSkillService jsService;

    private HashMap<String, Integer> getSkillTitles(){
        List<Skill> skills = skillService.getAllSkills();
        HashMap<String, Integer>skillTitles = new HashMap<>();
        for (Skill skill : skills) {
            skillTitles.put(skill.getSkillTitle(), 0);
        }
        return skillTitles;
    }

    private HttpResponse<String> getAPIResponse(String targetURL) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(targetURL))
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }
    


    @Override
    @Transactional
    public String getAPICourseData(String keyword) throws IOException, InterruptedException {
        String result = "Update Failed";
        System.out.println(result);
        keyword = keyword.replace(" ", "_");
        String targetURL = baseURL + "request_coursedata/?keyword=" + keyword;
        HttpResponse<String> response = getAPIResponse(targetURL);
        if(response == null){
            targetURL = baseURL + "crawl_coursedata/?keyword=" + keyword;
            response = getAPIResponse(targetURL);
        }else{
            result = "Response Ok! ";
            System.out.println(result);
        }
        ObjectMapper mapper = new ObjectMapper();
        List<CourseData> coursesData = mapper.readValue(response.body(),
            new TypeReference<List<CourseData>>() { });
        if(coursesData != null){
            result += "Course Data Ok! ";
            System.out.println(result);
        }
        Skill skill = skillService.findSkillByTitle(keyword.replace("_", " "));
        
        for (CourseData cd : coursesData) {
            CourseCrawled cc = new CourseCrawled();
            cc.setCourseTitle(cd.getCourseTitle());
            String url = "https://www.youtube.com/watch?v=" +cd.getCourseId();
            cc.setUrlLink(url);
            cc.setViews(Long.parseLong(cd.getViews()));
            cc.setLikes(Long.parseLong(cd.getLikes()));
            cc.setSubscribers(Long.parseLong(cd.getSubscribers()));
            cc.setChannelName(cd.getChannelName());
            String uploadDate = cd.getUploadDate();
            LocalDate dateup = LocalDate.parse(uploadDate, formatter);
            cc.setDate(dateup);
            cc.setDescription(cd.getDescription());
            cc.setDuration(cd.getDuration());
            cc.setSkill(skill);
            cc.setThumbnail(cd.getThumbnail());
            cwService.saveCourseCrawled(cc);

        }
        return result;

    }

    @Override
    @Transactional
    public String getAPIJobData(String keyword) throws IOException, InterruptedException {
        String result = "Update Failed";
        keyword = keyword.replace(" ", "_");
        String targetURL = baseURL + "request_jobdata/?keyword=" + keyword;

        HttpResponse<String> response = getAPIResponse(targetURL);
        if(response == null){
            targetURL = baseURL + "crawl_jobdata/?keyword=" + keyword;
            response = getAPIResponse(targetURL);
        }else{
            result = "Response Ok! ";
        }
            ObjectMapper mapper = new ObjectMapper();
            List<JobData> jobsData = mapper.readValue(response.body(),
                new TypeReference<List<JobData>>() { });
        if(jobsData != null){
                    result += "Job Data Ok! ";
        }

        HashMap<String, Integer> skillTitles = getSkillTitles();
        for (JobData jobData : jobsData) {
            String title = jobData.getJobDescription();
            // jdRepo.save(jobData);
            skillTitles.forEach((key,value)->{
                if(title.contains(key)){
                    skillTitles.put(key, value+1);
                }
            });
        }

        HashMap<String, Integer> sortedSkillTitles = utility.sortStrIntHM(skillTitles);
        List<String> skillsName = new ArrayList<>(sortedSkillTitles.keySet());
        
        Job job = new Job();
        job.setJobTitle(keyword.replace("_", " "));
        jobService.createJob(job);

        for(int i = 0; i<5; i++){
            Skill skill = skillService.findSkillByTitle(skillsName.get(i));
            JobSkill js = new JobSkill();
            js.setJob(job);
            js.setSkill(skill);
            js.setLastUpdatedDate(todayDate);
            jsService.createJobSkill(js);
        }
        System.out.println("Kaung is Handsome");
        return result;
        
    }

    @Override
    @Transactional
    public String getAllAPICourseData(String password) throws IOException, InterruptedException {
        String result = "";
        if(password != "iamfromteam2"){
            return "Password incorrect";
        }
        List <String> keywords = skillList;
        for(String key: keywords){
            result = getAPICourseData(key);
        }
        return result;
    }

    @Override
    @Transactional
    public String getAllAPIJobData(String password) throws IOException, InterruptedException {
        String result = "";
        if(password != "iamfromteam2"){
            return "Password incorrect";
        }

        List <String> keywords = jobList;
        for(String key: keywords){
           result = getAPIJobData(key);
        }
        return result;
    }

}