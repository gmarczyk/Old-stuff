<%-- 
    Document   : index
    Created on : Feb 2, 2017, 5:11:17 PM
    Author     : MarczykPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Primality test</title>
        <meta charset="UTF-8">
        <meta name="viewport" 
              content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <h1 ALIGN="CENTER">Welcome to the primality test page!</h1>
        <hr>

        <h3>Fermat test</h3>
        <form ACTION="CalcServlet"
              METHOD="POST">
            
            <p>
                Put the number You want to test:<br>
                The number has to be minimum 2
                <br>
                <INPUT TYPE="TEXT" NAME="testedNumber">
            </p> 
            
            <p>
                Put the values on which the test will be performed, separated by spaces.<br> 
                These values have to be in interval <1,P) where P stands for number which has to be tested
                <br>
                <INPUT TYPE="TEXT" NAME="testedOnValues" SIZE="150">
            </p>
          
            <INPUT TYPE="SUBMIT" VALUE="Perform Fermat's test">
             <br><br><hr>       
        </form>
        
        
        <form ACTION="DataServlet" 
             METHOD="POST">
            
            <p>
                History of calculations<br>
                <INPUT TYPE="SUBMIT" VALUE="Show history">
            </p>  
        </form>

        <form ACTION="CookieServlet" 
             METHOD="POST">
            
            <p>
                Cookies showing how many<br>
                <INPUT TYPE="SUBMIT" VALUE="Show cookies">
            </p>  
        </form>
        
    </body>
</html>

