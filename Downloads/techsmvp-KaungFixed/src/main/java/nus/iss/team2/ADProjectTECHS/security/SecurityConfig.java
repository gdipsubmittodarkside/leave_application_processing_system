package nus.iss.team2.ADProjectTECHS.security;



import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import nus.iss.team2.ADProjectTECHS.Repository.MemberRepository;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    /*
     * visitor - home, login, search , viewCourses
     * member - dashboard
     * */

    private static final String[] INGORE_PATH = {
            "/",
            "/DataTable/**",
            "/js/**",
            "/css/**",
            "/images/**",
            "/webjars/**",
            "/members/register**",
            "/courses**",
            "/search**",
            "/login**",
            "/images/**/**",
            "/api/**",
            "/oauth/**",
            "/search/**/**",
            "/search/**",
            "/skills/**/**",
            "/skills/**",
            "/courses/**/**",
            "/courses/**","/myCourses/**","/myCourses/**/**"
    };
    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors();

        http.authorizeRequests()
                .antMatchers(INGORE_PATH)
                .permitAll();
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/search/skills/home")
                .permitAll();
        http.oauth2Login().loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {

                        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

                        if(!memberService.processOAuthPostLogin(oAuth2User.getName(), oAuth2User.getEmail())){
                            response.sendRedirect("/members/register");
                        } else{
                            response.sendRedirect("/search/skills/home");
                        }
                    };
                });

        http.logout().logoutSuccessUrl("/search/skills/home").permitAll();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


        @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // allow post 5000
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }






}
