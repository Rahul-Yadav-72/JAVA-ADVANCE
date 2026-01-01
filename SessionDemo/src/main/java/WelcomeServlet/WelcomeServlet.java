package WelcomeServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// retrive session from Loginservlet
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession();
		//ArrayList<String> loginhistory = (ArrayList<String> session.getAttribute("loginhistory"));
		ArrayList<String> loginhistory = (ArrayList<String>) session.getAttribute("loginhistory");

		
		// get username from LoginServlet page 
		
		String username = (String)request.getAttribute("username");
		
		//print user and display welcome message 
		
		pw.println("<h2> Welcome ,"+ username+"</h2>");
		pw.println("<h2> Login history</h2>");
		
		if(loginhistory != null && !loginhistory.isEmpty()) {
			pw.println("<ul>");
			for(String loginTime:loginhistory) {
				pw.println("<li> Login time : "+loginTime+"</li>");
				
			}
		} else {
			pw.println("<h1> No login history </h1>");
			
		}
		response.getWriter().println("<a herf='LogOutServlet'>Logout </a>");
		
	
		
	}

}
