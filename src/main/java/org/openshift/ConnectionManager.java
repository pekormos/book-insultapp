
package oracleconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
//databaseDriverType, databaseIP, databasePort, databaseSID, databaseUser, databaseUser
    public static Connection getOracleConnection(String iDatabaseDriverType, String iDatabaseIP, String iDatabasePort, String iDatabaseSID, String iDatabaseUser, String iDatabasePassword) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle"+ ":" + iDatabaseDriverType + ":" + "@" + iDatabaseIP + iDatabasePort + ":" + iDatabaseSID, iDatabaseUser, iDatabasePassword);
    }

}
