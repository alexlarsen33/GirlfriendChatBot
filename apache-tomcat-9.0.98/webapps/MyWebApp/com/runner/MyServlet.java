package com.runner;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the message from the request
        String userMessage = request.getParameter("message");

        // Print the user's message to the console
        //System.out.println("Message from user: " + userMessage);

        // Set the response type
        response.setContentType("text/plain");

        // Respond with "Hello"
        response.getWriter().write("Hello");
    }
	
}
