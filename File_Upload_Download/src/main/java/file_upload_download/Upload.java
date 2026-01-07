package file_upload_download;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;


/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,      // 1MB
	    maxFileSize = 1024 * 1024 * 20,        // 20MB
	    maxRequestSize = 1024 * 1024 * 25      // 25MB
	)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");

	        Part filePart = request.getPart("file");
	        String fileName = filePart.getSubmittedFileName();
	        String fileType = filePart.getContentType();
	        InputStream fileData = filePart.getInputStream();
	        PrintWriter pw = response.getWriter();

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/employee",
	                "root",
	                ""
	            );

	            String sql = "INSERT INTO file_upload (file_name, file_type, file_data) VALUES (?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, fileName);
	            ps.setString(2, fileType);
	            ps.setBlob(3, fileData);

	            int result = ps.executeUpdate();

	            if (result > 0) {
	                //response.getWriter().println("<h3>File uploaded successfully</h3>");
	            	pw.println("<h3 style='color:green'>File uploaded successfully</h3>");
	                pw.println("<a href='Download'><button>View File List</button></a>");
	                pw.println("<a href='index.jsp'><button>Add New</button></a>");
	            } else {
	               // response.getWriter().println("<h3>File upload failed</h3>");
	            	pw.println("<h3 style='color:red'>File upload failed</h3>");
	            }

	            ps.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
	        }
	        
	
	}

}
