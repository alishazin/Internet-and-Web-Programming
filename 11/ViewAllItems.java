// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class ViewAllItems extends HttpServlet {
    
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
        String title = "All Items";
        
        String docType = "<!doctype HTML>\n";
        
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body>\n" +
            "<h1 align = \"center\">" + title + "</h1>\n<a href='http://localhost:8080/Home'>Back Home</a><hr/>"
        );
        
        try {

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, price, stock_left FROM products";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Float price = rs.getFloat("price");
                Integer stock_left = rs.getInt("stock_left");

                //Display values
                out.println("ID: " + id + "<br>");
                out.println("Name: " + name + "<br>");
                out.println("Price: " + price + "<br>");
                out.println("Stock Left: " + stock_left + "<br><hr/>");
            }
            out.println("</body></html>");

            // Clean-up environment
            rs.close();
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