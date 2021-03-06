//import java.sql.*;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.sql.Statement; 
import java.sql.SQLException;
import java.sql.DriverManager;

public class OracleConnect {
	public static void main(String args[]) {
        
       String databaseUser = System.getenv("ORACLE_ELINK_USER");
       String databasePassword = System.getenv("ORACLE_ELINK_PASSWORD");
       String databaseIP =  System.getenv("ORACLE_SERVICE_IP");
       String databasePort = System.getenv("ORACLE_SERVICE_PORT");
       String databaseSID = System.getenv("ORACLE_DATABASE_SERVICE_ID");
		Connection con= null;
     try {
			//	USE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//	CONNECT TO DB
            		con=DriverManager.getConnection("jdbc:oracle:thin:@"+ databaseIP + ":" + databasePort + ":" + databaseSID, databaseUser, databasePassword);

			//	CREATE STATEMENT
			Statement stmt=con.createStatement();

			//	EXECUTE QUERY
			ResultSet rs=stmt.executeQuery("select * from SLA_PENALTY");		//	FOR LONGER QUERIES {}
			while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
			//	CLOSE DB CONNECTION
			rs.close();
            con.close();
			}
		catch(Exception e){ System.out.println(e);}
	}
}
