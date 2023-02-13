package nus.iss.team2.ADProjectTECHS.Model.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(value = "courseId")
    String courseId;
    @JsonProperty(value = "courseTitle")
    String courseTitle;
    @JsonProperty(value = "embeddedURL")
    String embeddedURL;
    @JsonProperty(value = "views")
    String views;
    @JsonProperty(value = "likes")
    String likes;
    @JsonProperty(value = "subscribers")
    String subscribers;
    @JsonProperty(value = "channelName")
    String channelName;
    @JsonProperty(value = "videoTitle")
    String videoTitle;
    @JsonProperty(value = "uploadDate")
    String uploadDate;
    @JsonProperty(value = "thumbnail")
    String thumbnail;
    @JsonProperty(value = "description")
    String description;
    @JsonProperty(value = "duration")
    String duration;
    @JsonProperty(value = "skill")
    String skill;

    public CourseData(String courseId, String courseTitle, String views, String likes, String subscribers,
            String description) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.views = views;
        this.likes = likes;
        this.subscribers = subscribers;
        this.description = description;
    }

}
