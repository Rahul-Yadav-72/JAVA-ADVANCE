package file_upload_download;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
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

@WebServlet("/View")
public class View extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VIEW_QUERY =
            "SELECT file_name, file_type, file_data FROM file_upload WHERE id=?";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null) {
            response.getWriter().println("Invalid File ID");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee",
                    "root",
                    ""
            );

            PreparedStatement ps = con.prepareStatement(VIEW_QUERY);
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String fileType = rs.getString("file_type");

                // 🔥 IMPORTANT: inline for view (not download)
                response.setContentType(fileType);
                response.setHeader(
                        "Content-Disposition",
                        "inline; filename=\"" + rs.getString("file_name") + "\""
                );

                InputStream in = rs.getBinaryStream("file_data");
                ServletOutputStream out = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                in.close();
                out.flush();
                out.close();
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("View Error: " + e.getMessage());
        }
    }
}
