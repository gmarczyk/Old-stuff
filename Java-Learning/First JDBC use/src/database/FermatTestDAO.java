package pl.polsl.java.marczyk.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import pl.polsl.java.marczyk.model.SingleProbabilisticTest;

/**
 * Data access object (DAO) responsible for accessing table with data about Fermat tests
 *
 * @author  Marczyk Grzegorz
 * @version 1.0
 */
public final class FermatTestDAO {
    
    
    /**
     * Adds a row containing data of a single Fermat test into proper table associated with the callers class.
     * 
     * @param  testedNum       number which is tested for primality
     * @param  testedOnValues  values on which the test was performed separated by spaces
     * @param  results         result for each value on which the test was performed separated by spaces 
     * 
     * @return 1 if adding was successful, 0 if failed for some reason, -1 connection wasn't set, -2 problem with SQL occurred
     */
    public static int addFermatTestUnit(String testedNum, String testedOnValues, String results) {
        
        if(pl.polsl.java.marczyk.database.ConnectorDB.getConnection() == null) {
            return -1;
        }
        
        try {      
            Statement statement = pl.polsl.java.marczyk.database.ConnectorDB.getConnection().createStatement();
            String    command   = "INSERT INTO FERMATTEST (TESTEDNUMBER, TESTEDONVALUES, RESULTS) "
                                + "VALUES('" + testedNum + "', '" + testedOnValues + "', '" + results + "')"; 
            
            statement.executeUpdate(command);           
        } catch (SQLException ex) {
            return -2;
        } catch (Exception ex)    {
            return 0;
        }
         
        return 1;
    }
    
    /**
     * Gets all rows stored in the database in proper table associated with callers class.
     * 
     * @return List of objects containing data about a single Fermat test
     * @throws SQLException if connection with the database is not set (== null) or if any references to SQL API fails
     */
    public static ArrayList<SingleProbabilisticTest> getAllRows() throws SQLException {
        
        ArrayList<SingleProbabilisticTest> rowList = new ArrayList<>();
        
        Statement statement = pl.polsl.java.marczyk.database.ConnectorDB.getConnection().createStatement();
        ResultSet rs        = statement.executeQuery("SELECT * FROM FERMATTEST");
        while (rs.next()) {
            
            ArrayList<String> testedOnValues = new ArrayList<>();                 // Needed to create SingleProbabilisticTest object
            ArrayList<String> results        = new ArrayList<>();
            
            String[] testedOnString = rs.getString("TESTEDONVALUES").split(" ");  // These strings are multiple numerical chains separated by spaces
            String[] resultsString  = rs.getString("RESULTS").split(" ");         // ,so get all of them to fill the array lists with separate values
          
            testedOnValues.addAll(Arrays.asList(testedOnString));
            results.addAll(Arrays.asList(resultsString));
            
            rowList.add(new SingleProbabilisticTest(rs.getString("TESTEDNUMBER"), testedOnValues, results));        
        } 
  
        return rowList;
    }
    
}
