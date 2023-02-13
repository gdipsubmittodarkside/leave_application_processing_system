package nus.iss.team2.ADProjectTECHS.Service.Impl;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Model.MyCourse;
import nus.iss.team2.ADProjectTECHS.Model.Skill;
import nus.iss.team2.ADProjectTECHS.Model.Data.CourseData;
import nus.iss.team2.ADProjectTECHS.Repository.CourseCrawledRepository;
import nus.iss.team2.ADProjectTECHS.Service.CourseCrawledService;


@Service
public class CourseCrawledServiceImpl implements CourseCrawledService {
    
    @Autowired
    private CourseCrawledRepository courseCrawledRepository;
    @Override
    public List<CourseCrawled> findCoursesSortedBySubscribers() {
        return courseCrawledRepository.findCoursesSortedBySubscribers();

    }

    @Override
    public List<CourseCrawled> findCoursesTitleLike(String title) {
        return courseCrawledRepository.findCourseCrawledsByCourseTitleContainsIgnoreCase(title);
    }

    @Override
    public List<CourseCrawled> findCoursesBySkill(Skill skill) {
        return courseCrawledRepository.findCoursesBySkill(skill);
    }

    @Override
    public List<CourseCrawled> findCoursesBySkillTitleLike(String skillTitle) {
        return courseCrawledRepository.findCoursesCrawledBySkillTitleContains(skillTitle);
    }

    @Override
    public List<CourseCrawled> findCoursesSortedByViews() {
        return courseCrawledRepository.findCoursesSortedByViews();
    }

    @Override
    public void cancelForeignKeyConstraint() {
        courseCrawledRepository.cancelForeignKeyConstraint();
    }


    @Override
    public void enableForeignKeyConstraint() {
        courseCrawledRepository.enableForeignKeyConstraint();
    }

    @Override
    public void truncateTable() {
        courseCrawledRepository.truncateTable();
    }

    @Override
    public List<CourseCrawled> getCourseCrawledList(){
        return courseCrawledRepository.findAll();
    }

    @Override
    public CourseCrawled saveCourseCrawled(CourseCrawled courseCrawled){
        return courseCrawledRepository.save(courseCrawled);
    }

    @Override
    public Optional<CourseCrawled> findCourseCrawled(long id){
        return courseCrawledRepository.findById(id);
    }

    @Override
    public CourseCrawled findCourseCrawledById(long id){
        return courseCrawledRepository.findCourseCrawledByCourseId(id);
    }

    @Override
    @Transactional
    public CourseCrawled updateCourseCrawled(CourseCrawled courseCrawled){
        return courseCrawledRepository.save(courseCrawled);
    }

    @Override
    public Boolean deleteCourseCrawledById(Long id){

        try{
            courseCrawledRepository.deleteById(id);
            return true;
        }

        catch(Exception e){
            return false;
        }
    }

    @Override
    public Page<CourseCrawled> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(1, 5);
        return courseCrawledRepository.findAll(pageable);
    }

    @Override
    public List<CourseCrawled> findCoursesBySkillId(long skillId) {
        return courseCrawledRepository.findCourseBySkillId(skillId);
    }

    @Override
    public CourseCrawled findCourseCrawledByUrl(String url){
        return courseCrawledRepository.findCourseCrawledByUrl(url);
    }

    // FOR COURSE RECOMMENDATION API CALLING
    @Override
    public List<CourseCrawled> recommend_best_match(String query, List<MyCourse> myCourses) {

        List<String> myCoursesIdList = myCourses
                .stream()
                .map(x -> x.getCourseUrl().substring(x.getCourseUrl().length() - 11)).toList();

        String API;

        try {
            if (!myCourses.isEmpty()) {
                API = "http://localhost:8089/recommend?query=" + query;
                for (String str : myCoursesIdList) {
                    API = API + "&" + str;
                }
            } else {
                API = "http://localhost:8089/recommend?query=" + query;
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(API))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();

            List<CourseData> courses = mapper.readValue(response.body(), new TypeReference<List<CourseData>>() {

            });

            List<CourseCrawled> courseCrawleds = courses
                    .stream()
                    .map(course -> courseCrawledRepository.findCourseCrawledByURL(course.getCourseId()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            return courseCrawleds;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }



        
    }


