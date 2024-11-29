import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

public class Endpoint extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }
    
    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.write(
            "<!DOCTYPE html>" + 
            "<head>" +
                "<title>" + req.getMethod() + " Method</title>" +
            "</head>" +
            "<body>" + 
                "<ul>"
        );

        Enumeration<String> params = req.getParameterNames();

        while (params.hasMoreElements()) {

            String paramName = params.nextElement();
            String paramValue = req.getParameter(paramName);

            out.write(
                "<li>" + paramName + " : " + paramValue + "</li>"
            );

        }

    }

}
