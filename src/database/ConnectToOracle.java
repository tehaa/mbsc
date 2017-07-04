package database;

import java.sql.*;
import java.util.*;

import java.io.*;

public class ConnectToOracle{ 
Connection conn= null; 
public ConnectToOracle(String dbURL, String user, String pass) {
try { 
Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();  

    
this.conn= DriverManager.getConnection(dbURL, user, pass);  
    System.out.println(conn);
System.out.println("Connection established"); 
}
catch (Exception ex) {
            ex.printStackTrace();
        }


}

public Connection returnConnection () {
return this.conn; 
}

}
