// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class UpdateItem extends HttpServlet {
    
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
            <title>Update Item</title>
        </head>
        <body>
            <form action="http://localhost:8080/UpdateItem" method="POST">
                <h1>Search Item To Update</h1>
                ID: <input type="number" name="id" required><br/><br/>
                <input type="submit" value="Search" />
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
        String title = "Update Item";
        
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
            sql = "SELECT id, name, price, stock_left FROM products WHERE id=" + request.getParameter("id");
            ResultSet rs = stmt.executeQuery(sql);
            Boolean isEmpty = true;

            // Extract data from result set
            while(rs.next()){                
                isEmpty = false;

                //Retrieve by column name
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Float price = rs.getFloat("price");
                Integer stock_left = rs.getInt("stock_left");

                //Display values
                out.println("""
                    <form action="http://localhost:8080/UpdateConfirm" method="POST">
                        <h1>Update Item</h1>
                        ID:&nbsp;""" + id + 
                        """
                        <input type='hidden' name='id' value='""" + id + "'" +
                        """ 
                        required>
                        <br /><br/>
                        Name: <input type="text" name="name" value='""" + name + "'" +
                        """
                        required>
                        <br /><br/>
                        Price: <input type="number" value='""" + price + "'" +
                        """
                        step="0.01" min="1" name="price" required/>
                        <br /><br/>
                        Initial Stock: <input type="number" name="stock_left" value='""" + stock_left + "'" + 
                        """
                        required/><br/><br/>
                        <input type="submit" value="Update" />
                        <hr/>
                        <a href="http://localhost:8080/Home">Back Home</a>
                    </form>
                """);
            }

            if (isEmpty) {
                out.println("<p>No record with the id '"+ request.getParameter("id") + "'</p><hr/><a href='http://localhost:8080/Home'>Back Home</a>");
            }

            out.println("</body></html>");

            // Clean-up environment
            rs.close();
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