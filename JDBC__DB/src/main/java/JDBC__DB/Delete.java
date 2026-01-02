package JDBC__DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		// id value fetch
		int id = Integer.parseInt(request.getParameter("id"));

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    Connection con = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/employee",
		            "root",
		            ""
		    );

		    PreparedStatement ps =
		            con.prepareStatement("DELETE FROM empdata WHERE empid=?");

		    ps.setInt(1, id);
		    ps.executeUpdate();

		    //  Redirect to Select servlet 
		    response.sendRedirect("Select");

		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

	

}
