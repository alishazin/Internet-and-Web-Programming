// Loading required libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;
 
public class Home extends HttpServlet {
    
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
            <title>Inventory Management System</title>
        </head>
        <body>
            <h1>Inventory Management System</h1>
            <hr>
            <a href="http://localhost:8080/AddItem">Add Item</a>
            <hr>
            <a href="http://localhost:8080/RemoveItem">Remove Item</a>
            <hr>
            <a href="http://localhost:8080/ViewAllItems">View All Item</a>
            <hr>
            <a href="http://localhost:8080/UpdateItem">Update Item</a>
        </body>
        </html>
        """);

    }

} 