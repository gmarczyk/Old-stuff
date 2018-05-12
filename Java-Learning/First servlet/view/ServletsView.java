package view;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * Class provides procedures mostly pasting HTML code to display what is wanted for each specific servlet.
 *
 * @author Marczyk Grzegorz
 * @version 1.4
 */
public class ServletsView {
    
    /** Output stream to the servlet (response) */
    PrintWriter out;
    
    
    /**
     * Default constructor.
     * @param outputStreamReference reference to instance of specific servlet output stream
     */
    public ServletsView(PrintWriter outputStreamReference) {
        
        this.out = outputStreamReference;
    }
    
    /** 
     * Pastes the initialization commands for each HTML site, sets the title marker etc. 
     * @param title is the title from head section of HTML site
     */
    public void initHtml(String title) {
        
        out.println("<!DOCTYPE html>");
        out.println("<html>"); 
        out.println("<head>");  
        out.println("<title>"+title+"</title>");     
        
        out.println("<STYLE type=\"text/css\">");
        out.println("<!--");
        out.println("TABLE {");
        out.println("border-collapse: collapse;"); 
        out.println("border:          solid;"); 
        out.println("border-width:    2px;"); 
        out.println("border-color: black;");        
        out.println("}");
        out.println("TD {");
        out.println("border:       solid;");         
        out.println("border-width: 1px;");
        out.println("border-color: black;");
        out.println("padding:      5px;");   
        out.println("}");
        out.println("TH {");  
        out.println("color:        white;");  
        out.println("background:   black;");  
        out.println("font-weight:  bolder;");  
        out.println("font-size:    12pt;");  
        out.println("border-width: 2px;");  
        out.println("border-color: black;");  
        out.println("padding:      15px;");  
        out.println("}");      
        out.println("-->");  
        out.println("</STYLE>");  

        
        out.println("</head>"); 
        out.println("<body>");
    }  
    
    /** 
     * Pastes commands to close HTML code.
     */
    public void closeHtml() {
        
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * Pastes names and values of all parameters passed to servlet through request.
     * @param refToRequest reference to specific request
     */
    public void showParameters(HttpServletRequest refToRequest) {
        
            out.println("<b>Parameters passed:</b><br><br>");  
            Enumeration paramList = refToRequest.getParameterNames();
            
            while(paramList.hasMoreElements()) {   
                
                String paramName  = (String) paramList.nextElement();
                String paramValue = (String) refToRequest.getParameter(paramName);
                out.println(paramName  + " = ");
                out.println(paramValue + "<br>");
            }
    }
    
    /**
     * Shows all the values passed through parameter in indexed list.
     * @param values is a list of all the values to be printed
     */
    public void showListedValues(String[] values) {
        
            if(values.length == 0) {
                out.println("<b>Empty values or error in splitting</b><br><br>");
            } 
            else {            
                out.println("<b>Values after splitting:</b><br><br>");
                
                int i = 1;
                for(String val : values){
                    out.println("<b>" + i + ".</b> " + val + "<br>");
                    i++;
                }
            }
    }

    /**
     * Prints actual state of the ProbabilisticTests class instance. Should be used just after test to show results.
     * 
     * @param number          the number on which the test was performed
     * @param testedOnNumber  values according to which the number is tested ("a" in common formula)
     * @param result          results for each value on which the number was tested
     */
    public void showProbabilisticTestsState(String number, ArrayList<String> testedOnNumber, ArrayList<String> result) {
        
        out.println("Tested number: [<b>" + number + "</b>]<br><br>");
        
        // Set table and column names
        out.println("<TABLE>");
        out.println("<TR>");
        out.println("<TH><b>Tested on number</b></TH>");
        out.println("<TH>Result</TH>");
        out.println("</TR>");

        for (int i = 0; i < result.size(); i++) { // Can be either result or testedOnNumber variable, both should be the same size
            
            String resultStr = result.get(i).toUpperCase();
            this.addRow_FermatResult(testedOnNumber.get(i), resultStr);
        }
        
        out.println("</TABLE>");
    }

    
    /**
     * Prints a row in HTML language with two cells.
     * 
     * @param testedOnNum first cell, one of values on which the number was tested
     * @param result      second cell, result of the test
     */
    private void addRow_FermatResult(String testedOnNum, String result) {
        
        out.println("<TR>");
        out.println("<TD>" + testedOnNum + "</TD>");
        out.println("<TD>" + result      + "</TD>");
        out.println("</TR>");
    }
}
