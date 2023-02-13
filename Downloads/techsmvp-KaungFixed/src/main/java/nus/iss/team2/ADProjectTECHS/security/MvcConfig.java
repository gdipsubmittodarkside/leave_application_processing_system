package nus.iss.team2.ADProjectTECHS.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("Others/403");
        registry.addViewController("/login").setViewName("Others/login");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/coffe")
//                .allowedOrigins("http://localhost:5000")
//                .allowedMethods("GET","POST", "PUT", "DELETE")
//                .allowCredentials(true);
//
//    }
}
