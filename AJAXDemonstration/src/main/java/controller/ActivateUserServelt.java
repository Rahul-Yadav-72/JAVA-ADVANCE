package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Servlet implementation class ActivateUserServelt
 */
@WebServlet("/ActivateUserServelt")
public class ActivateUserServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateUserServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("html/text");
		PrintWriter pw=response.getWriter();
		Set<String> users =(Set<String>)getServletContext().getAttribute("loggedUser");
		if(users == null || users.isEmpty()) {
			pw.println("no active user");
			
		}else {
			for(String u: users) {
				pw.print("<li>"+u+"</li>");
			}
		}
	}

}
