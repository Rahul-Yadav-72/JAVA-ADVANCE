package select;

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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class select
 */
@WebServlet("/select")
public class select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public select() {
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
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("empname");
		String mobile = request.getParameter("mobileno");
		
		try {
			// load driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Connection establish
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			
			//create statement using PreparedStatment 
			
			PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM empdata WHERE empname=? AND mobileno=?");

            ps.setString(1, name);
            ps.setString(2, mobile);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<h2>Employee Found</h2>");
                pw.println("Name: " + rs.getString("empname") + "<br>");
                pw.println("Mobile: " + rs.getString("mobileno"));
            } else {
                pw.println("<h2>No Employee Found</h2>");
            }

            con.close();
        } catch (Exception e) {
            pw.println("Error: " + e);
        }
		
	}
	

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
