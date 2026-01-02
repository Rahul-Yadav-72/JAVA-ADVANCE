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
import java.sql.ResultSet;

/**
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
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

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee",
                    "root",
                    ""
            );

            // SQL Query
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empdata");
            ResultSet rs = ps.executeQuery();

            pw.println("<h2>Employee Details</h2>");

            pw.println("<table border='1'>");
            pw.println("<tr>");
            pw.println("<th>Emp ID</th>");
            pw.println("<th>Emp Name</th>");
            pw.println("<th>Mobile No</th>");
            pw.println("<th>Delete</th>");
            pw.println("<th>Edit</th>");
            pw.println("</tr>");

            while (rs.next()) {
                int id = rs.getInt(1);

                pw.println("<tr>");
                pw.println("<td>" + id + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");

                pw.println("<td><a href='Delete?id=" + id + "'>Delete</a></td>");
                pw.println("<td><a href='EditSelect?id=" + id + "'>Edit</a></td>");
                pw.println("</tr>");
            }

            pw.println("</table>");

            

          

        } catch (Exception e) {
            pw.println("<h3>Exception Error : " + e + "</h3>");
           
        }
        
        pw.println("<button type='submit' name = 'index'><a href='index.jsp'>Home</a></button> ");
	}

	
}
