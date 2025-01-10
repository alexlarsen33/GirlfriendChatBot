import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the message from the HTML form
        String message = request.getParameter("message");

        // Set the response content type
        response.setContentType("text/html");

        // Respond back to the user
        response.getWriter().write("<h1>Message received: " + message + "</h1>");
    }
}
