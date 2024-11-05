// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class DatabaseAccess extends HttpServlet {
    
    // JDBC driver name and database URL
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    String DB_URL = "jdbc:mysql://localhost:3306/TEST";

    //  Database credentials
    String USER = "root";
    String PASS = "<password>";
    Connection conn = null;

    public void init() throws ServletException {
        
        try {
            
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch(Exception se) {
            se.printStackTrace();
        } 

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "All Marks";
        String heading = "All Records";
        
        String docType = "<!doctype HTML>\n";
        
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body>\n" +
            "<h1 align = \"center\">" + title + "</h1>\n"
        );
        
        try {

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, mark FROM student_mark";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Integer id  = rs.getInt("id");
                String name  = rs.getString("name");
                Float mark = rs.getFloat("mark");

                //Display values
                out.println("ID: " + id + "<br>");
                out.println("Name: " + name + "<br>");
                out.println("Mark: " + mark + "<br><hr/>");
            }
            out.println("</body></html>");

            // Clean-up environment
            rs.close();
            stmt.close();

        } catch(Exception e) {
            e.printStackTrace();
        }  
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Record Inserted";
        
        String docType = "<!doctype HTML>\n";
        
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body>\n"
        );
        
        try {

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO student_mark(name, mark) VALUES('"+ request.getParameter("name") + "', " + request.getParameter("mark") + ")";
            stmt.execute(sql);
            out.println("<p>Record Inserted Successfully!</p>");

            out.println("</body></html>");

            // Clean-up environment
            stmt.close();

        } catch(Exception e) {
            e.printStackTrace();
        } 

    }

    public void destroy() { 
  
        // Close connection object. 
        try { 
            conn.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 

} 