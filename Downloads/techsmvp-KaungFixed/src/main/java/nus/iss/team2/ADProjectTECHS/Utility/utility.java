package nus.iss.team2.ADProjectTECHS.Utility;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class utility {

    public static HashMap<String, Integer> sortStrIntHM(HashMap<String, Integer> hm){
        HashMap<String, Integer> temp
            = hm.entrySet()
                  .stream()
                  .sorted((i1, i2)
                              -> i2.getValue().compareTo(
                                  i1.getValue()))
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1, LinkedHashMap::new));

        return temp;
    }
}
