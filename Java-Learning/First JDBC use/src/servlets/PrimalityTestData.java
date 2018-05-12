package pl.polsl.java.marczyk.servlets;

import pl.polsl.java.marczyk.model.SingleProbabilisticTest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.java.marczyk.database.FermatTestDAO;
import pl.polsl.java.marczyk.view.ServletsView;

/**
 * Servlet responsible for access to data-functionality of program
 * 
 * @author   Marczyk Grzegorz
 * @version  1.4
 */
public class PrimalityTestData extends HttpServlet {
    
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
            view.initHtml("History of tests");
            
            try {
                
                ArrayList<SingleProbabilisticTest> testHistory = null;     // Prepare variable for getting history
                testHistory = FermatTestDAO.getAllRows();

                if(testHistory == null || testHistory.isEmpty()) {
                    out.println("<br><br> <h2 ALIGN=\"CENTER\"> <b> NO TESTS IN DATABASE </b> </h2>");
                }
                else {
                    for (int i = 0; i < testHistory.size(); i++) {

                         out.println("<div ALIGN=\"CENTER\">");
                         view.showProbabilisticTestsState(testHistory.get(i).getTestedNum(),
                                                          testHistory.get(i).getTestedOnValues(),
                                                          testHistory.get(i).getResults());
                         out.println("<br><hr><br> </div>");
                    }    
                }  
                
            } catch (SQLException ex) {
                Logger.getLogger(PrimalityTestData.class.getName()).log(Level.SEVERE, null, ex);
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

        return "[PrimalityTestData] Servlet which displays history of performed tests for primality"; 
    }

}
