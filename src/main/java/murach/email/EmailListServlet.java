package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import murach.business.User;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {

        String url = "/index.jsp"; // Mặc định là index.jsp

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.jsp";    // the "join" page
        }
        else if (action.equals("add")) {                
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // store data in User object
            User user = new User(firstName, lastName, email);
            // UserDB.insert(user); // Comment, không cần nếu không dùng DB

            // set User object in request object and set URL
            request.setAttribute("user", user);
            
            GregorianCalendar currentDate = new GregorianCalendar();
            int currentYear = currentDate.get(Calendar.YEAR);
            request.setAttribute("currentYear", currentYear);
            
            url = "/thanks.jsp";   // the "thanks" page
        }
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                         throws ServletException, IOException {
        doPost(request, response);
    }    
}