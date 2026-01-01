package Session;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String username=request.getParameter("uname");
		String password=request.getParameter("pwd");
		
		// Validate username and password 
		if("rahul".equals(username)&&"12345".equals(password)) {
			// get current data and time
			LocalDateTime now = LocalDateTime.now();
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			String currentlogintime=now.format(formatter);
			
			//Start the Session implement 
			HttpSession session = request.getSession();
			ArrayList<String> loginhistory = (ArrayList<String>)session.getAttribute("loginhistory");
			
			if(loginhistory==null) {
				loginhistory = new ArrayList<>();	
			
			}
			// add the current login time and date 
			loginhistory.add(currentlogintime);
			session.setAttribute("loginhistory",loginhistory);
			request.setAttribute("username", username);
			// setAttribute("session",variable );
			
			//request.getRequestDispatcher("welcomeservlet").forward(request,response);
			request.getRequestDispatcher("WelcomeServlet").forward(request, response);

			
		   }else {
			// invalid login 
			response.getWriter().println("invalid user and password <a herf='index.jsp' </a>");
			   
			
	       }
		
	}

}
