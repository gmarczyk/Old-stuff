package pl.polsl.java.marczyk.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 * Responsible for setting connection between server and database.
 * 
 * @author  Marczyk Grzegorz
 * @version 1.0
 */
public final class ConnectorDB {
    
    /** Connection instance */
    private static Connection dbConnection = null;
    
    
    /**
     * Sets the connection instance according to servlets DB access parameters. If a connection already exists, closes it 
     * and opens new one.
     * 
     * @param  context is the context of a servlet that stores access data required to connect to the database.
     * @return TRUE if connection was successful, FALSE if failed. 
     */
    public static boolean connectToDB(ServletContext context) {
        
        if(ConnectorDB.dbConnection != null) {
            ConnectorDB.disconnect();
        }
        
        try {
            Class.forName(context.getInitParameter("DB_Driver"));
            ConnectorDB.dbConnection = DriverManager.getConnection(context.getInitParameter("DB_Url"),
                                                                   context.getInitParameter("DB_Login"),
                                                                   context.getInitParameter("DB_Password"));

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            return false;
        }   
        
        return true;
    }
    
    /**
     * Getter of the database connection instance
     * @return the connection (may be null if didn't initialized)
     */
    public static Connection getConnection() {
        
        return ConnectorDB.dbConnection;
    }

    /**
     * Closing current connection with database.
     * @return TRUE if disconnected properly, FALSE if failed to
     */
    public static boolean disconnect() {
        
        try { 
            ConnectorDB.dbConnection.close();
        } catch (SQLException e) {
            return false; 
        } 
        return true;
    }
    
}
