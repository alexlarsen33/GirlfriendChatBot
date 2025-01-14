package com.runner;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
	public static HttpServletResponse currentResponse = null;
	private static String userResponse = "";
	
	public static Girlfriend gf = null;
	
	
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the message from the request
		
		
		
        String userMessage = request.getParameter("message");
		
		if (gf == null) {
			gf = new Girlfriend(userMessage); // The first message will be the name of the girlfriend
		}
		
		userResponse = userMessage;

        // Print the user's message to the console
        //System.out.println("Message from user: " + userMessage);

        // Set the response type
        response.setContentType("text/plain");

        // Respond with "Hello"
		currentResponse = response;
        response.getWriter().write(gf.checkForTriggers(userMessage) + gf.askQuestion());
    }
	
	public static void sendMessage(String message) throws ServletException, IOException {
		if (currentResponse == null) {
			System.out.println("Cannot send message, currentResponse is null.");
		}else{
			currentResponse.getWriter().write(message);
		}
	}
	
	public static void sendMessage(HttpServletResponse response, String message) throws ServletException, IOException {
		response.getWriter().write(message);
	}
	
	public static String getMessage() {
		return userResponse;
	}
	
}
