package oracleconnection;

public class DBpropertyDownloader {

 public static void getDbProperty(String &strDbIP, int &intDbPort, String &strDbSID, String &strDbUser, String &strDbPassword) {
        strDbUser = System.getenv("ORACLE_ELINK_USER");
        strDbPassword = System.getenv("ORACLE_ELINK_PASSWORD");
        strDbIP =  System.getenv("ORACLE_SERVICE_IP");
        intDbPort = System.getenv("ORACLE_SERVICE_PORT");
        strDbSID = System.getenv("ORACLE_DATABASE_SERVICE_ID");
    }

}