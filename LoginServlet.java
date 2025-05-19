package wad_external;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    // Store user credentials (in a real app, use a database)
    private static final Map<String, String> users = new HashMap<>();
    // Track login counts
    private static final Map<String, Integer> loginCounts = new HashMap<>();

    static {
        // Add some test users
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username != null && password != null &&
            users.containsKey(username) && users.get(username).equals(password)) {

            // Increment login count
            int count = 1;
            if (loginCounts.containsKey(username)) {
                count = loginCounts.get(username) + 1;
            }
            loginCounts.put(username, count);

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Welcome</title></head><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("<p>You have logged in " + count + " time(s).</p>");
            out.println("</body></html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Login Failed</title></head><body>");
            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<a href='index.html'>Try again</a>");
            out.println("</body></html>");
        }
    }
}