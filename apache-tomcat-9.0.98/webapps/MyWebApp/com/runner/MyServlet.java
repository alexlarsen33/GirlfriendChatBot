package com.runner;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
	public HttpServletResponse currentResponse = null;
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the message from the request
        String userMessage = request.getParameter("message");

        // Print the user's message to the console
        //System.out.println("Message from user: " + userMessage);

        // Set the response type
        response.setContentType("text/plain");

        // Respond with "Hello"
		currentResponse = response;
        //response.getWriter().write("Hello");
    }
	
	public void sendMessage(String message) {
		if (currentResponse == null) {
			System.out.println("Cannot send message, currentResponse is null.");
		}else{
			currentResponse.getWriter().write(message);
		}
	}
	
	public void sendMessage(HttpServletResponse response, String message) {
		response.getWriter().write(message);
	}
	
}
