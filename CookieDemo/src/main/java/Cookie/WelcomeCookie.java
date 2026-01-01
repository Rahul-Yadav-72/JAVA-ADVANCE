package Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class WelcomeCookie
 */
@WebServlet("/WelcomeCookie")
public class WelcomeCookie extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Cookie[] cookies = request.getCookies();
		String uname = null;
		String pwd = null;

		if (cookies != null) {
			for (Cookie ck : cookies) {
				if ("username".equals(ck.getName())) {
					uname = ck.getValue();
				}
				if ("password".equals(ck.getName())) {
					pwd = ck.getValue();
				}
			}
		}

		if (uname != null && pwd != null) {
			out.println("<h1>Welcome Page</h1>");
			out.println("<p><b>Username:</b> " + uname + "</p>");
			out.println("<p><b>Password:</b> " + pwd + "</p>");
			
			out.println("<p><a href='LogoutCookie'>Logout</a></p>");
		} else {
			out.println("<h1>No user login found</h1>");
			out.println("<a href='login.jsp'>Login</a>");
		}
	}
}
