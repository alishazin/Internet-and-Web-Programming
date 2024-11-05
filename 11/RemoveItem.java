// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class RemoveItem extends HttpServlet {
    
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Add Item</title>
        </head>
        <body>
            <form action="http://localhost:8080/RemoveItem" method="POST">
                <h1>Remove Item</h1>
                ID: <input type="number" name="id" required><br/><br/>
                <input type="submit" value="Remove" />
                <hr/>
                <a href="http://localhost:8080/Home">Back Home</a>
            </form>
        </body>
        </html>
        """);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Remove Item";
        
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
            sql = "DELETE FROM products WHERE id=" + request.getParameter("id");
            stmt.execute(sql);
            out.println("<p>Item Removed Successfully!</p><hr/><a href='http://localhost:8080/Home'>Back Home</a>");
            
            out.println("</body></html>");
            
            // Clean-up environment
            stmt.close();
            
        } catch(Exception e) {
            e.printStackTrace();
            out.println("<p>" + e.getLocalizedMessage() +"</p>");
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