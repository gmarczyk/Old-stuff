import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProbabilisticTests;
import model.ValueOutOfRequiredIntervalException;
import view.ServletsView;

/**
 * Servlet responsible for access to calculation-functionality of program
 * 
 * @author   Marczyk Grzegorz
 * @version  1.4
 */
public class PrimalityTestCalc extends HttpServlet {
    
    
    /** Allows to perform probabilistic primality tests such as Fermat test */
    private ProbabilisticTests modelProbTests;
    
    /** Pastes specific HTML commands and sets the structure of page when servlet responses */
    private ServletsView       view;
    
    
    /**
     * Initializes things affiliated with servlet once in servers life 
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init() throws ServletException {
        
        modelProbTests = new ProbabilisticTests();
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
             
            Enumeration paramList = request.getParameterNames();   
            if(paramList.hasMoreElements() == false) {
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "Cant find parameters! Contact support!");
                return; // This simply stops the servlet
            } 
            
            while(paramList.hasMoreElements()) {
               
                String paramName  = (String) paramList.nextElement();
                String paramValue = (String) request.getParameter(paramName);
                
                if(paramValue == null || paramValue.equals("")) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Passed parameters are empty or null!");
                    return; 
                }
            }
            
            view = new ServletsView(out);
            view.initHtml("Fermat test");
            
            view.showParameters(request);
            out.println("<br><hr><br>");
             
            String[] valuesList = (request.getParameter("testedOnValues")).split(" ");
            view.showListedValues(valuesList);
            out.println("<br><hr><br>");
            
            // Set number
            try {
                modelProbTests.setNumber(request.getParameter("testedNumber")); 
            } catch (ValueOutOfRequiredIntervalException ex) {        
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "The number must be pure numerical chain and minimum = 2!");
                return;
            } catch (IllegalArgumentException ex) {
                response.sendError(HttpServletResponse.SC_CONFLICT,  "Empty value given somewhere");
                return;
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_CONFLICT,  "[1] Unexpected error occured, contact MarczykG@gmail.com");
                return;
            }
            
            // Set values
            modelProbTests.cleanTestedOnNum();
            for(String val : valuesList) {   
                
                try {
                    modelProbTests.setTestOnNumber(val);                       
                } catch (ValueOutOfRequiredIntervalException ex) {  
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, 
                            "Some values are not pure numerical chains or are beyond the required interval! "
                          + "The value that caused error is: [" + val + "]. "
                          + "Remember that spaces and white characters can cause fail too."); 
                    return;
                } catch (IllegalArgumentException ex) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Empty or null value on which the test was ment to be performed! Check for unwanted spaces");
                    return;
                } catch (Exception ex) {
                    response.sendError(HttpServletResponse.SC_CONFLICT,  "[2] Unexpected error occured, contact MarczykG@gmail.com");
                    return;
                } 
            }

            // Perform fermat test
            try {
                modelProbTests.cleanResults();
                modelProbTests.calculateFermatTest();
            } catch (ValueOutOfRequiredIntervalException ex) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "[Model - ProbabilisticTests] Values passed arent pure numerical chains");
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "[Model - ProbabilisticTests] Problem with data occured");
                return;
            }
            
            String            testNumber  = modelProbTests.getNumber();
            ArrayList<String> testValues  = modelProbTests.getTestedOnNumber();
            ArrayList<String> testResults = Convert_BooleanToStringArray(modelProbTests.getResult());
            
            view.showProbabilisticTestsState(testNumber, testValues, testResults);
            
            // Session tracking etc.
            ArrayList<SingleProbabilisticTest> testsHistory = null;
            HttpSession session = request.getSession(true);
            Object getHistory = session.getAttribute("TestHistory");
            
            if (getHistory == null) {
                testsHistory = new ArrayList<SingleProbabilisticTest>();
            } 
            else {
                testsHistory = (ArrayList<SingleProbabilisticTest>) getHistory;
            } 
            testsHistory.add(new SingleProbabilisticTest(testNumber, testValues, testResults));
            session.setAttribute("TestHistory", testsHistory);
            
            // Check if should create a cookie or only replace value in existing one 
            Cookie[] cookies = request.getCookies();
            Cookie   foundCookie = null;
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("AmountInLast5min")) {
                    foundCookie = cookie;
                }
            }
            
            if(foundCookie == null) {
                Cookie tmpCookie = new Cookie("AmountInLast5min","1");
                tmpCookie.setMaxAge(60*5);
                response.addCookie(tmpCookie);
            }
            else {
                int tmpValue = Integer.parseInt(foundCookie.getValue());
                tmpValue++;
                foundCookie.setValue(Integer.toString(tmpValue));
                response.addCookie(foundCookie);
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

        return "[PrimalityTestCalc] Servlet which has access to model that does primality probabilistic tests"; 
    }
    
    /**
     * Converts ArrayList of Boolean type to String ArrayList
     * @param boolArrayList ArrayList Boolean  to be converted
     * @return reference to instance of String ArrayList
     */
    private ArrayList<String> Convert_BooleanToStringArray(ArrayList<Boolean> boolArrayList) {
        
        ArrayList<String> tmpStringList = new ArrayList<>();
        for (int i = 0; i < boolArrayList.size(); i++) {
            tmpStringList.add(boolArrayList.get(i).toString().toUpperCase());
        }
        return tmpStringList;
    }
}
