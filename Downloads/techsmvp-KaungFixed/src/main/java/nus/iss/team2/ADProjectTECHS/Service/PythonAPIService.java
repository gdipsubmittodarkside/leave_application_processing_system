package nus.iss.team2.ADProjectTECHS.Service;

import java.io.IOException;
import java.util.List;


public interface PythonAPIService {
    String getAllAPICourseData(String password) throws IOException, InterruptedException;
    String getAPICourseData(String keyword) throws IOException, InterruptedException;

    String getAllAPIJobData(String password) throws IOException, InterruptedException;
    String getAPIJobData(String keyword) throws IOException, InterruptedException;
}
