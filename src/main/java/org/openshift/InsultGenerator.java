package org.openshift; 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.sql.SQLException;


public class InsultGenerator { 
	public String generateInsult() { 
 	 	String querry_result = ""; 
		//String newline = System.getProperty("line.separator");
		String newline = System.lineSeparator();
		String databaseURL = "";
		String username = "";
		String password = "";
        
        try {
            	databaseURL = "jdbc:postgresql://"; 
 		databaseURL += System.getenv("POSTGRESQL_SERVICE_HOST"); 
 		databaseURL += "/" + System.getenv("POSTGRESQL_DATABASE"); 
 		username = System.getenv("POSTGRESQL_USER"); 
 		password = System.getenv("PGPASSWORD"); 
        } catch (Exception e) {
            System.out.println("Getting DB parameters failed");
            return "Getting DB parameters failed";
        }
		
         System.out.println("databaseURL :" + databaseURL + ", username :" + username + ", password :" + password);
 		
        try {
            Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
                                + "Include in your library path!");
			e.printStackTrace();
			return "Where is your PostgreSQL JDBC Driver? Include in your library path!";

		}        
        System.out.println("PostgreSQL JDBC Driver Registered!");
        
        Connection testconnection = null;
        try {
	    testconnection = DriverManager.getConnection(databaseURL, username, password);
            if (testconnection != null) { 
            String SQL_SLA_PENALTY = "select * from SLA_PENALTY";
            
            String returnstring =  "";          
            //returnstring += "Sikeres a testconnection! databaseURL :" + databaseURL + ", username :" + username + ", password :" + password + newline;
            Statement stmt = testconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SLA_PENALTY); 
            returnstring += "Content of SLA_PENALTY" + System.getProperty("line.separator");
            //returnstring += "SLA_NAME, LIMIT_DEV_MIN, LIMIT_DEV_MAX, PENALTY_REL, VALID_FROM, VALID_TO" + newline;
            returnstring +=  String.format("%-30.30s %-30.30s %-30.30s %-30.30s %-30.30s  %-30.30s", "SLA_NAME", "LIMIT_DEV_MIN", "LIMIT_DEV_MAX", "PENALTY_REL", "VALID_FROM", "VALID_TO");
            returnstring += newline;


            while (rs.next()) { 
                       returnstring +=  String.format("%-30.30s %-30.30s %-30.30s %-30.30s %-30.30s  %-30.30s" + newline, rs.getString("SLA_NAME"), rs.getString("LIMIT_DEV_MIN"), rs.getString("LIMIT_DEV_MAX"), rs.getString("PENALTY_REL"), rs.getString("VALID_FROM"),rs.getString("VALID_TO")); 
                      returnstring += newline;
                      
            } 
            rs.close();
            
            testconnection.close();
            return returnstring; 
            }
    	} catch (Exception e) {
            	System.out.println("Testconnection Failed! Check output console");
		e.printStackTrace();
		return "TestConnection Failed! Check output console";
		}
    	 
         
 	try { 
		Connection connection = DriverManager.getConnection(databaseURL, username, password); 
		if (connection != null) { 
 				//String SQL = "select a.string AS first, b.string AS second, c.string AS noun from short_adjective a , long_adjective b, noun c ORDER BY random() limit 1"; 
                String SQL_SLA_PENALTY = "select   p.SLA_NAME as sla_name,"
                                                + "p.LIMIT_DEV_MIN AS limit_dev_min,"
                                                + "p.LIMIT_DEV_MAX AS limit_dev_max,"
                                                + "p.PENALTY_REL AS penalty_rel,"
                                                + "p.VALID_FROM AS valid_from,"
                                                + "p.VALID_TO AS valid_to"
                                                + "from SLA_PENALTY p";

                String SQL_SLA_SOLUTION = "select so.SOLUTION AS solution,"
                                                    + "so.RECORD_DATE AS record_date,"	        
                                                    + "so.CUSTOMER AS customer,"   	                            
                                                    + "so.NUMBER_ACTIVE_SPS AS number_of_active_sps,"
                                                    + "so.CANCELLATION_DATE AS cancellation_date" 
                                                    + "from SLA_SOLUTION so";
                 
               String SQL_SLA_SERVICELEVEL = "select   se.SLA_NAME AS sla_name,"  
                                                    + "se.SPCOMMIT_VALUE AS spcommit_value,"
                                                    + "se.SOLCOMMIT_FLAG AS solcommit_flag,"                       
                                                    + "se.SLA_TYPE	AS sla_type,"	                        
                                                    + "se.SLA_VERSION AS sla_version,"	                        
                                                    + "se.PROCESS_FLAG	AS process_flag,"                
                                                    + "se.VALID_FROM AS valid_from,"    				
                                                    + "se.VALID_TO AS valid_to"              
                                                    + "from SLA_SERVICELEVEL se";
  

                Statement stmt = connection.createStatement();
                
 				ResultSet rs = stmt.executeQuery(SQL_SLA_PENALTY); 
                querry_result += "Content of SLA_PENALTY:" + newline;
                querry_result += "SLA_NAME, LIMIT_DEV_MIN, LIMIT_DEV_MAX, PENALTY_REL, VALID_FROM, VALID_TO" + newline;
 				while (rs.next()) { 
                       querry_result +=  String.format("%s, %s, %s, %s, %s, %s" + newline, rs.getString("sla_name"), 
 					                    rs.getString("limit_dev_min"), rs.getString("limit_dev_max"), rs.getString("penalty_rel"), rs.getString("valid_from"),rs.getString("valid_to")); 
 				} 
 				rs.close();
                querry_result += newline;
                
 				rs = stmt.executeQuery(SQL_SLA_SOLUTION); 
                querry_result += "Content of SQL_SLA_SOLUTION:" + newline;;
                querry_result += "SOLUTION, RECORD_DATE, CUSTOMER, NUMBER_ACTIVE_SPS, CANCELLATION_DATE" + newline;
 				while (rs.next()) { 
                       querry_result =  String.format("%s, %s, %s, %s, %s " + newline, rs.getString("solution"), 
 					                    rs.getString("record_date"), rs.getString("customer"), rs.getString("number_of_active_sps"), rs.getString("cancellation_date")); 
 				} 
 				rs.close();
                querry_result += newline;

                rs = stmt.executeQuery(SQL_SLA_SERVICELEVEL); 
                querry_result += "Content of SQL_SLA_SERVICELEVEL:" + newline;
                querry_result += "SLA_NAME, SLA_TYPE, SLA_VERSION, SOLCOMMIT_FLAG, SPCOMMIT_VALUE, PROCESS_FLAG, VALID_FROM, VALID_TO" + newline;
 				while (rs.next()) {
                       querry_result +=  String.format("%s, %s, %s, %s, %s, %s, %s, %s" + newline, rs.getString("sla_name"), 
 					                    rs.getString("spcommit_value"), rs.getString("solcommit_flag"), rs.getString("sla_type"), rs.getString("sla_version"), rs.getString("process_flag"), rs.getString("valid_from"), rs.getString("valid_from")); 
 				} 
 				rs.close();
    		connection.close(); 
 			} 
 		} catch (Exception e) { 
 			return "Database connection problem!"; 
 		} 
 		return querry_result; 
 	} //public String generateInsult()
} 
