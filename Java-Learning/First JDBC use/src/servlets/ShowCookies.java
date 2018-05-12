package pl.polsl.java.marczyk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.java.marczyk.view.ServletsView;

/**
 * Servlet responsible for handling cookies.
 * 
 * @author   Marczyk Grzegorz
 * @version  1.0
 */
public class ShowCookies extends HttpServlet {
    
    /** Pastes specific HTML commands and sets the structure of page when servlet responses */
    private ServletsView view;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param  request           servlet request
     * @param  response          servlet response
     * @throws ServletException  if a servlet-specific error occurs
     * @throws IOException       if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;"
                              + "charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {  

            view = new ServletsView(out);
            view.initHtml("Cookies");
            
            Cookie[] cookies = request.getCookies();
            Cookie   foundCookie = null;
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("AmountInLast5min")) foundCookie = cookie;
            }
            
            if(foundCookie == null) {
                out.println("<br><br> <h2 ALIGN=\"CENTER\"> <b> NO COOKIES REGISTERED IN LAST 5 MINUTES </b> </h2>");
            }
            else {
                out.println("<br><br> <h2 ALIGN=\"CENTER\"> <b> YOU PERFORMED: [" + foundCookie.getValue() + "]"
                        + " TESTS IN THE LAST 5 MINUTES</b> </h2>");
            }
            
            
            view.closeHtml();
        }   
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param  request           servlet request
     * @param  response          servlet response
     * @throws ServletException  if a servlet-specific error occurs
     * @throws IOException       if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        processRequest(request, response);   
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param  request           servlet request
     * @param  response          servlet response
     * @throws ServletException  if a servlet-specific error occurs
     * @throws IOException       if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {

        return "[ShowCookies] Servlet which displays cookies etc."; 
    }

}
