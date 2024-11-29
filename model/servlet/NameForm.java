// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class NameForm extends HttpServlet {

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Using " + request.getMethod() + " Method to Read Form Data";
		String docType = "<!doctype HTML>";
		
		out.println(docType +
		"<html>\n" +
			"<head><title>" + title + "</title></head>\n" +
			"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<ul>\n" +
					"  <li><b>First Name</b>: "
					+ request.getParameter("fname") + "\n" +
					"  <li><b>Last Name</b>: "
					+ request.getParameter("lname") + "\n" +
				"</ul>\n" +
			"</body>" +
		"</html>"
		);
	
	}

}