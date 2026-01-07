package file_upload_download;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/Download")
public class Download extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String LIST_QUERY =
            "SELECT id, file_name FROM file_upload";

    private static final String DOWNLOAD_QUERY =
            "SELECT file_name, file_type, file_data FROM file_upload WHERE id=?";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee",
                    "root",
                    ""
            );

            /* ================= FILE LIST ================= */
            if (id == null) {

                response.setContentType("text/html");
                PrintWriter pw = response.getWriter();

                pw.println("<html>");
                pw.println("<head><title>File List</title></head>");
                pw.println("<body>");

                pw.println("<h2>File List</h2>");

                pw.println("<table border='1' cellpadding='8' cellspacing='0'>");

                pw.println("<tr>");
                pw.println("<th>ID</th>");
                pw.println("<th>File Name</th>");
                pw.println("<th>View</th>");
                pw.println("<th>Download</th>");
                pw.println("<th>Delete</th>");
                pw.println("</tr>");

                PreparedStatement ps = con.prepareStatement(LIST_QUERY);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    pw.println("<tr>");

                    pw.println("<td>" + rs.getInt("id") + "</td>");
                    pw.println("<td>" + rs.getString("file_name") + "</td>");

                    // VIEW
                    pw.println("<td>");
                    pw.println("<a href='View?id=" + rs.getInt("id") + "' target='_blank'>");
                    pw.println("<button>View</button>");
                    pw.println("</a>");
                    pw.println("</td>");

                    // DOWNLOAD
                    pw.println("<td>");
                    pw.println("<a href='Download?id=" + rs.getInt("id") + "'>");
                    pw.println("<button>Download</button>");
                    pw.println("</a>");
                    pw.println("</td>");

                    // DELETE
                    pw.println("<td>");
                    pw.println("<a href='Delete?id=" + rs.getInt("id") + "' "
                            + "onclick=\"return confirm('Are you sure?')\">");
                    pw.println("<button style='color:red'>Delete</button>");
                    pw.println("</a>");
                    pw.println("</td>");

                    pw.println("</tr>");
                }

                pw.println("</table>");
                pw.println("<a href='index.jsp'><button>Add New</button></a>");
                pw.println("</body>");
                pw.println("</html>");
                

                return; // 🔥 important
            }
            
            



            /* ================= FILE DOWNLOAD ================= */
            else {

                PreparedStatement ps = con.prepareStatement(DOWNLOAD_QUERY);
                ps.setInt(1, Integer.parseInt(id));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    response.setContentType(rs.getString("file_type"));
                    response.setHeader(
                            "Content-Disposition",
                            "attachment; filename=\"" + rs.getString("file_name") + "\""
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
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Download Error: " + e.getMessage());
        }
    }
}
