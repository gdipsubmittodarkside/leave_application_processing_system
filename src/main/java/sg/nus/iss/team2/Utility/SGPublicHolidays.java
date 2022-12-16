package sg.nus.iss.team2.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SGPublicHolidays {

    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private String counties;
    private String launchYear;
    private String type;

    /* Sample JSON
        date:"2019-02-18" ("yyyy-MM-dd")
        localName:"Washington's Birthday"
        name:"Presidents' Day"
        countryCode:"US"
        fixed:false
        global:true
        counties:null
        launchYear:null
        type:"Public"
     */
}
