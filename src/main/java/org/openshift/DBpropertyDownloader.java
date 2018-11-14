package oracleconnection;

public class DBpropertyDownloader {

 public static void getDbProperty(Object oracleDbParameters) {
        oracleDbParameters.databaseUser = System.getenv("ORACLE_ELINK_USER");
        oracleDbParameters.databasePassword = System.getenv("ORACLE_ELINK_PASSWORD");
        oracleDbParameters.databaseIP =  System.getenv("ORACLE_SERVICE_IP");
        oracleDbParameters.databasePort = System.getenv("ORACLE_SERVICE_PORT");
        oracleDbParameters.databaseSID = System.getenv("ORACLE_DATABASE_SERVICE_ID");
    }

}
