package JDBC__DB;

import jakarta.servlet.ServletException;
import java.sql.Connection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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
			
			PreparedStatement ps = con.prepareStatement("insert into empdata (empname,mobileno) values (?,?)");
			
			ps.setString(1, name);
			ps.setString(2, mobile);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("<h2> insert data  successfully </h2>");
				
			} else {
				pw.println("<h2> Data not registered successfully </h2>");
				
			}
			
			// 🔹 Go to Home Button (ADDED)
            pw.println("<br><a href='index.jsp'><button>Go to Home</button></a>");

		}
		catch(SQLException e) {
			pw.println("Exception Erro "+e);
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
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
