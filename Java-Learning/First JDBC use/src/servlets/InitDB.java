package pl.polsl.java.marczyk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.java.marczyk.database.ConnectorDB;
import pl.polsl.java.marczyk.view.ServletsView;

/**
 * Servlet responsible for creating a connection between server and database.
 * 
 * @author   Marczyk Grzegorz
 * @version  1.0
 */
public class InitDB extends HttpServlet {
    
    /** Pastes specific HTML commands and sets the structure of page when servlet responses */
    private ServletsView view;
    
    /** Stores information about result of connecting with database */
    private boolean      gotConnected = false;
   
    
     /**
      * Initializes things associated with servlet once in servers life 
      */
    @Override
    public void init() {
        
       if(ConnectorDB.connectToDB(this.getServletContext())){
           this.gotConnected = true;
       }
    }
    

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
            
            if(this.gotConnected == false){
                 out.println("<h1><b> Initialization of database FAILED </b> </h1>");
            }
            else {
                 out.println("<h1><b> Initialization of database SUCCEDED </b> </h1>");
            }
            
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

        return "[InitDB] Initializes connection with database"; 
    }

}
