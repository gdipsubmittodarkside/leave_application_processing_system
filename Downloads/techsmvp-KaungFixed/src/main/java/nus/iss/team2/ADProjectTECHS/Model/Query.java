package nus.iss.team2.ADProjectTECHS.Model;

import lombok.*;
import nus.iss.team2.ADProjectTECHS.Model.Enums.SearchType;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
public class Query {
    private SearchType searchType;

    @Pattern(regexp = "^[A-Za-z0-9#]*$" , message= "{error.queryString.limitSpecialCharacter}")
    private String queryString;


}
