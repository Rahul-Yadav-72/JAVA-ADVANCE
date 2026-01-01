package Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginCookie")
public class LoginCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// User entered values
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");

		// Correct credentials
		String correctUser = "admin";
		String correctPass = "123";

		if (correctUser.equals(username) && correctPass.equals(password)) {

			Cookie userCookie = new Cookie("username", username);
			Cookie passCookie = new Cookie("password", password);

			userCookie.setMaxAge(30 * 60);
			passCookie.setMaxAge(30 * 60);

			response.addCookie(userCookie);
			response.addCookie(passCookie);

			response.sendRedirect("WelcomeCookie");

		} else {

			out.println("<h2 style='color:red'>Login Failed</h2>");
			out.println("<p><b>Entered Username:</b> " + username + "</p>");
			out.println("<p><b>Entered Password:</b> " + password + "</p>");

			if (correctUser.equals(username)) {
				out.println("<p style='color:red'>Reason: Wrong Password</p>");
			} else {
				out.println("<p style='color:red'>Reason: Invalid Username</p>");
			}

			out.println("<a href='login.jsp'>Try Again</a>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
