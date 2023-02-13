package nus.iss.team2.ADProjectTECHS.Model.Data;

import javax.persistence.Column;
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
public class JobData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(value = "jobTitle")
    String jobTitle;
    @JsonProperty(value = "postedDate")
    String postedDate;
    @JsonProperty(value = "jobDescription")
    String jobDescription;
    @JsonProperty(value = "url")
    String url;
    @JsonProperty(value = "jobType")
    String jobType;
    
}
