//import java.sql.*;
//package org.openshift; 
package oracleconnection;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.sql.Statement; 
import java.sql.SQLException;

class DbParams {
            String databaseSID;
            String databaseUser;
            String databasePassword;
            String databaseIP;
            String databasePort;  
        };  

public class ConnectToOracle {
        //Session session;
        Connection connection;
        DbParams oracleDbParameters;
        String databaseURL = "";
   
        String databaseDriverType = "thin"; // thin, oci, kprb.. 
    
        protected boolean connectDB() {
            try {
                connection = ConnectionManager.getOracleConnection(databaseDriverType, oracleDbParameters.databaseIP, oracleDbParameters.databasePort, oracleDbParameters.databaseSID, oracleDbParameters.databaseUser, oracleDbParameters.databaseUser);
  
            } catch (SQLException e) {
    
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                   System.out.println("SSH Tunnel failed!");
                e.printStackTrace();
                return false;
            }
            if (connection != null) {
                return true;
            } else {
                System.out.println("Failed to make connection!");
                return false;
            }
        }
    
    
        public String DownloadDbContent(String strRemoteTableName) {
            String remote_table_name = strRemoteTableName;
            String returnstring = "";
            String newline = System.getProperty("line.separator");
            
            try {
                //DBpropertyDownloader.getDbProperty(oracleDbParameters);
                oracleDbParameters.databaseUser = System.getenv("ORACLE_ELINK_USER");
                oracleDbParameters.databasePassword = System.getenv("ORACLE_ELINK_PASSWORD");
                oracleDbParameters.databaseIP =  System.getenv("ORACLE_SERVICE_IP");
                oracleDbParameters.databasePort = System.getenv("ORACLE_SERVICE_PORT");
                oracleDbParameters.databaseSID = System.getenv("ORACLE_DATABASE_SERVICE_ID");
            } catch (Exception e) {
                    System.out.println("Getting DB parameters failed");
                    return "Getting DB parameters failed";
            }
                
           /* try {
                databaseUser = System.getenv("ORACLE_ELINK_USER");
                databasePassword = System.getenv("ORACLE_ELINK_PASSWORD");
            
                databaseURL = "jdbc:oracle:thin:@"; 
                databaseURL += System.getenv("ORACLE_SERVICE_IP"); 
                databaseURL += ":" + System.getenv("ORACLE_SERVICE_PORT"); 
                databaseURL += ":" + System.getenv("ORACLE_DATABASE_SERVICE");
                } catch (Exception e) {
                    System.out.println("Getting DB parameters failed");
                    return "Getting DB parameters failed";
                }
             */   
			//	USE DRIVER
            try {
                //The forName() method of Class class is used to register the driver class. This method is used to dynamically load the driver class.
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                returnstring += "Where is your Oracle JDBC Driver?";
                e.printStackTrace();
                return returnstring;
            }

           try {
            if (connectDB()) {
                Statement stmt=connection.createStatement();
                String query = "select * from " + remote_table_name;
                ResultSet rs=stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                int coloumnType;
                String coloumnName;
               
               // Hashtable<String, String> hashtable = new Hashtable<String, String>();
                
                while(rs.next()) {
                    returnstring += rs.getString(1) + " " + "%n";
                    /*
                    for (int i = 1; i <= columnsNumber; i++) {
                        coloumnType = rsmd.getColumnType(i);
                        coloumnName = getColumnName(i);
                        if (coloumnType == Types.VARCHAR || coloumnType == Types.CHAR) {
                            out.print(rs.getString(i));
                        } else {
                            out.print(rs.getLong(i));
                        }*/
                
                    }   
                rs.close();
                connection.close();
                return returnstring;
                }
             } catch (Exception e) {
            	System.out.println("Connection to ORACLE DB Failed! Check output console");
                e.printStackTrace();
                return "Connection to ORACLE DB Failed! Check output console";
            }
         return returnstring;
         }
}
