package file_upload_download;

import jakarta.servlet.ServletException;
import java.io.OutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int fileId = Integer.parseInt(request.getParameter("id"));

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/empdata?useSSL=false&serverTimezone=UTC",
	                "root",
	                ""
	            );

	            String sql = "SELECT file_name, file_type, file_data FROM file_upload WHERE id=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, fileId);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                String fileName = rs.getString("file_name");
	                String fileType = rs.getString("file_type");
	                InputStream fileData = rs.getBinaryStream("file_data");

	                response.setContentType(fileType);
	                response.setHeader(
	                    "Content-Disposition",
	                    "attachment; filename=\"" + fileName + "\""
	                );

	                OutputStream out = response.getOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead;

	                while ((bytesRead = fileData.read(buffer)) != -1) {
	                    out.write(buffer, 0, bytesRead);
	                }

	                fileData.close();
	                out.flush();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Download Error: " + e.getMessage());
	        }
	}

	

}
