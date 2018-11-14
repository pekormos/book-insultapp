
package oracleconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
//databaseDriverType, databaseIP, databasePort, databaseSID, databaseUser, databaseUser
    public static Connection getOracleConnection(String iDatabaseDriverType, String iDatabaseIP, int iDatabasePort, String iDatabaseSID, String iDatabaseUser, String iDatabasePassword) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle"+ ":" + iDatabaseDriverType + ":" + "@" + iDatabaseIP + iDatabasePort + ":" + iDatabaseSID, iDatabaseUser, iDatabasePassword);
    }


    public static Connection getOracleConnection(String databaseDriverType, String databaseIP, int databasePort, String databaseSID, String databaseUser, String databasePassword){
        // TODO Auto-generated method stub
        return null;
    }

}
