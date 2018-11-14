package oracleconnection;

public class DBpropertyDownloader {
    

 public static void getDbProperty(DbParams iOracleDbParameters) {
        iOracleDbParameters.databaseUser = System.getenv("ORACLE_ELINK_USER");
        iOracleDbParameters.databasePassword = System.getenv("ORACLE_ELINK_PASSWORD");
        iOracleDbParameters.databaseIP =  System.getenv("ORACLE_SERVICE_IP");
        iOracleDbParameters.databasePort = System.getenv("ORACLE_SERVICE_PORT");
        iOracleDbParameters.databaseSID = System.getenv("ORACLE_DATABASE_SERVICE_ID");
    }

}
