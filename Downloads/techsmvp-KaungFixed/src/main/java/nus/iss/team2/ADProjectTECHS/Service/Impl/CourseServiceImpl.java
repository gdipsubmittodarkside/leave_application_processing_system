package nus.iss.team2.ADProjectTECHS.Service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;

import nus.iss.team2.ADProjectTECHS.Model.CourseCrawled;
import nus.iss.team2.ADProjectTECHS.Repository.CourseCrawledRepository;
import nus.iss.team2.ADProjectTECHS.Service.CourseService;
import reactor.core.publisher.Flux;


@Service
public class CourseServiceImpl implements CourseService {

    private final WebClient webClient;
    @Autowired
    private CourseCrawledRepository courseCrawledRepository;

    public CourseServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://python")
                .build();
    }




    @CrossOrigin(origins = "http://localhost:5000")
    public List<CourseCrawled> getAndSaveCrawledCourses() {
        Flux<CourseCrawled>  coursesCrawledFlux =  webClient.get()
                .uri("/courses")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(CourseCrawled.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });

        List<CourseCrawled> courseCrawledsList =  coursesCrawledFlux.collectList().block();

        if(courseCrawledsList.size() > 0) {
            courseCrawledRepository.deleteAll();
            courseCrawledRepository.saveAllAndFlush(courseCrawledsList);
        }
        return courseCrawledsList;
    }




}
