package sg.nus.iss.team2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "public_holiday")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicHoliday {
   
    @JsonProperty(value = "localName")
    private String localName;
    @JsonProperty(value = "name")
    private String name;
    @Id
    @JsonProperty(value = "date")
    private String date;
    @JsonProperty(value = "countryCode")
    private String countryCode;
    @JsonIgnore
    private boolean fixed;
    @JsonIgnore
    private boolean global;

}
