package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");


		 // PrintWriter pw=response.getWriter();


		 HttpSession session=request.getSession(false);


		 if(session !=null)


		 {


		 String user= (String)session.getAttribute("user");


		 session.invalidate();


		 ServletContext context=getServletContext();


		 synchronized(context) {


		 Set<String> users=(Set<String>)context.getAttribute("loggedUser");


		 if(users !=null && user !=null)


		 {


		 users.remove(user);


		 } //inner if 


		 } // synchronized


		 } //outer if 


		 response.sendRedirect("Login.jsp");


	}


}
