 package sg.nus.iss.team2.interceptor;

 import org.springframework.stereotype.Component;
 import org.springframework.web.servlet.HandlerInterceptor;
 import sg.nus.iss.team2.model.User;
 import sg.nus.iss.team2.model.UserSession;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

 @Component
 public class SecurityInterceptor implements HandlerInterceptor {
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         System.out.println("Intercepting " + request.getRequestURI());
         String uri = request.getRequestURI();
         if (uri.startsWith("/css/") || uri.startsWith("/image/")) {
             return true;
         }

         if (uri.equalsIgnoreCase("/") || uri.equalsIgnoreCase("/home")
                 || uri.equalsIgnoreCase("/login")
                 || uri.equalsIgnoreCase("/about")) {
             return true;
         }

         if (uri.startsWith("/home/")) {
             return true;
         }

         HttpSession session = request.getSession();
         UserSession userSession = (UserSession) session.getAttribute("userSession");
         if ( userSession == null) {
             // No username, meaning not logged in yet
             // Redirect to login page
             response.sendRedirect("/login");
             return false;
         }

         User userFromSession = userSession.getUser();
         if (uri.startsWith("/admin") && !userFromSession.getRoleIds().contains(1L)) {
              throw new Exception();
          }

          if (uri.startsWith("/manager") && !userFromSession.getRoleIds().contains(3L)) {
              throw new Exception();
          }


         // OK, forward to Controller
         return true;

     }
 }
