package com.maintenance;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * MaintenanceServlet handles the display of either a maintenance banner 
 * or a welcome page based on the 'maintenanceMode' context parameter.
 */
public class MaintenanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set the content type for the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // 1. Access the ServletContext to read application-wide parameters
        ServletContext context = getServletContext();
        
        // 2. Read context-param values from web.xml
        // Provides default values if parameters are missing to ensure stability
        String appName = context.getInitParameter("appName");
        if (appName == null) appName = "Our Application";
        
        String mode = context.getInitParameter("maintenanceMode");
        String message = context.getInitParameter("maintenanceMessage");
        if (message == null) message = "We are currently undergoing maintenance.";
        
        // Start HTML Output
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + appName + "</title>");
        
        // CSS for layout and the Maintenance Banner styling
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; color: #333; }");
        out.println(".container { width: 80%; margin: 50px auto; text-align: center; background: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 20px rgba(0,0,0,0.1); }");
        
        // Maintenance Banner: Yellow background and bold text as per requirements
        out.println(".maintenance-banner { background-color: #fff3cd; color: #856404; border: 1px solid #ffeeba; padding: 20px; border-radius: 5px; margin-bottom: 30px; font-size: 1.2rem; }");
        out.println(".warning-icon { font-size: 2rem; margin-bottom: 10px; }");
        
        out.println("h1 { color: #007bff; }");
        out.println("h2 { color: #28a745; }");
        out.println("p { font-size: 1.1rem; line-height: 1.5; }");
        out.println("</style>");
        out.println("</head>");
        
        out.println("<body>");
        out.println("<div class='container'>");
        
        // 3. Always show the Application Name
        out.println("<h1>" + appName + "</h1>");
        
        // 4. Conditional Logic: Check if maintenanceMode is "true"
        if ("true".equalsIgnoreCase(mode)) {
            // Maintenance Mode: Display the styled banner and message
            out.println("<div class='maintenance-banner'>");
            out.println("<div class='warning-icon'>⚠️</div>");
            out.println("<strong>UNDER MAINTENANCE</strong>");
            out.println("<p>" + message + "</p>");
            out.println("</div>");
        } else {
            // Normal Mode: Display a welcome message
            out.println("<h2>Welcome to our website!</h2>");
            out.println("<p>We are glad to have you here. Feel free to explore our services.</p>");
        }
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
