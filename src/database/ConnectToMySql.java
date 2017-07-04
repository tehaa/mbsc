
package database ;
import java.sql.*; 
import java.util.*; 
//import java.org.*; 
import java.io.*; 

public class ConnectToMySql{ 
Connection conn= null; 
public ConnectToMySql(String dbURL, String user, String pass) { 

try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();

    
this.conn = DriverManager.getConnection(dbURL, user, pass);
System.out.println("connection established!!"); 
}
catch (Exception ex) {
            ex.printStackTrace();
        } 

}

public Connection returnConnection () {
return this.conn; 
}
}
