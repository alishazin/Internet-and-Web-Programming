// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class UpdateConfirm extends HttpServlet {
    
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Updated Item";
        
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
            sql = "UPDATE products SET name='"+ request.getParameter("name") + "', price=" + request.getParameter("price") + ", stock_left=" + request.getParameter("stock_left") + " WHERE id=" + request.getParameter("id");
            stmt.execute(sql);
            out.println("<p>Item Updated Successfully!</p><hr/><a href='http://localhost:8080/Home'>Back Home</a>");
            
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