package database ;

import java.sql.*; 
import java.util.*; 
import java.io.*; 

public class ConnectToSqlServer{ 
Connection conn= null; 
public ConnectToSqlServer(String dbURL, String user, String pass) { 

         try {
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); /* however, that is not required since JDBC 4.0 (JDK 6.0) because the driver manager can detect and load the driver class automatically as long as the sqljdbc4.jar is present in the classpath */ 

this.conn = DriverManager.getConnection(dbURL, user, pass);
System.out.println("Connection established"); 
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
         }     


public Connection returnConnection () {
return this.conn; 
}

}
