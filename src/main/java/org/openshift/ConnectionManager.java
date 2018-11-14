/**
 *
 */

package oracleconnection;

//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
//databaseDriverType, databaseIP, databasePort, databaseSID, databaseUser, databaseUser
    public static Connection getOracleConnection(String databaseDriverType, String databaseIP, int databasePort, String databaseSID, String databaseUser, String databasePassword) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle"+ ":" + databaseDriverType + ":" + "@" + databaseIP + databasePort + ":" + databaseSID, databaseUser, databasePassword);
    }


    public static Connection getOracleConnection(String databaseDriverType, String databaseIP, int databasePort, String databaseSID, String databaseUser, String databasePassword){
        // TODO Auto-generated method stub
        return null;
    }

}
