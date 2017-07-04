package connect ;
import database.ConnectToOracle;
import database.ConnectToMySql;
import database.ConnectToSqlServer;
import java.sql.*; 
import java.util.*; 
//import java.org.*; 
import java.io.*; 
import java.lang.*; 
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class db { 
     static boolean b = false ;

    public static void createOracleDatabase(String dbURL, String name, String user, String pass) {

        Connection conn = null;
        String folder = "dot properties files";

        ConnectToOracle c = new ConnectToOracle(dbURL, user, pass);
        conn = c.returnConnection();
        if (conn == null) {

            b = true;

        } else {
//saving in the properties file. 
            File dir = new File(folder); //creating the folder in the current directory
            dir.mkdir();
            Properties p = new Properties();
//filling the properties data structure with the url/username/password
            p.setProperty("dbURL", dbURL);
            p.setProperty("username", user);
            p.setProperty("password", pass);
            p.setProperty("dbType", "Oracle");
            File localFile = new File(folder + "\\" + name + ".properties"); // creating the .properties file based on the given name to the db
            if (localFile.exists()) {
                JOptionPane.showMessageDialog(null, "data base is exists ", "Success", 2, new ImageIcon("Ok.png"));
            } else {
                try {
                    FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
                    p.store(localFileOutputStream, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




public static void createMySqlDatabase(String name, String dbURL, String user, String pass) { 
Connection conn= null;  
String folder= "dot properties files";
ConnectToMySql c= new ConnectToMySql(dbURL, user, pass); 
conn= c.returnConnection(); 
if(conn==null){
 
b=true ;

}
//saving in the properties file. 

File dir= new File(folder); //creating the folder in the current directory
dir.mkdir(); 
Properties p= new Properties(); 
//filling the properties data structure with the url/username/password
p.setProperty("dbURL", dbURL); 
p.setProperty("username", user); 
p.setProperty("password", pass); 
p.setProperty("dbType", "mySql"); 
      File localFile = new File(folder + "\\" + name +  ".properties"); // creating the .properties file based on the given name to the db
if(localFile.exists()) 
  JOptionPane.showMessageDialog(null,"data base is exists ","Success", 2 , new ImageIcon("Ok.png"));
else {
try { 
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
p.store(localFileOutputStream, null);
}
catch (FileNotFoundException e) {
e.printStackTrace(); 	
}
catch (IOException e) {
e.printStackTrace(); 
}
}
}

public static void createSqlServerDatabase(String name, String dbURL, String user, String pass) { 
Connection conn= null;  
String folder= "dot properties files";
ConnectToSqlServer c= new ConnectToSqlServer(dbURL, user, pass); 
conn= c.returnConnection(); 
//saving in the properties file. 

File dir= new File(folder); //creating the folder in the current directory
dir.mkdir(); 
Properties p= new Properties(); 
//filling the properties data structure with the url/username/password
p.setProperty("dbURL", dbURL); 

p.setProperty("username", user); 
p.setProperty("password", pass); 
p.setProperty("dbType", "sqlServer"); 
      File localFile = new File(folder + "\\" + name +  ".properties"); // creating the .properties file based on the given name to the db
if(localFile.exists()) 
System.out.println("There exists a database with the same name!! "); 
else {
try { 
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
p.store(localFileOutputStream, null);
}
catch (FileNotFoundException e) {
e.printStackTrace(); 	
}
catch (IOException e) {
e.printStackTrace(); 
}
}
}



public static void openExistingDatabase(String name) {
Connection conn= null; 	
String folder= "dot properties files";
Properties p= new Properties(); 
FileInputStream localFileInputStream= null;
    try
    {
      File localFile = new File(folder + "\\" + name  + ".properties");
      localFileInputStream = new FileInputStream(localFile);
p.load(localFileInputStream); 
if(p.getProperty("dbType").equals("oracle")){
ConnectToOracle c= new ConnectToOracle(p.getProperty("dbURL"), p.getProperty("username"), p.getProperty("password") ); 
conn= c.returnConnection(); 
}
if(p.getProperty("dbType").equals("sqlServer")) {
ConnectToSqlServer c= new ConnectToSqlServer(p.getProperty("dbURL"), p.getProperty("username"), p.getProperty("password")); 
conn= c.returnConnection(); 
}
if(p.getProperty("dbType").equals("mySql")) {
ConnectToMySql c= new ConnectToMySql(p.getProperty("dbURL"), p.getProperty("username"), p.getProperty("password")); 
conn= c.returnConnection(); 
}
    }    catch (Exception localException)
    {
      localFileInputStream = null;
    }

}

public static void addNewDatabaseName(String name) { 
File file= new File("names of databases.txt"); //where names of all the existing databases exist
BufferedWriter bw= null; 
FileWriter fw= null; 
try{
if(!file.exists())
file.createNewFile(); 
fw= new FileWriter(file.getAbsoluteFile(), true); 
bw= new BufferedWriter(fw); 
bw.write(name); 
bw.newLine(); 

}catch(IOException e) {
e.printStackTrace(); 
}
try {
if(bw != null)
bw.close(); 
if(fw != null) 
fw.close(); 
}catch (IOException x){
x.printStackTrace(); 
}

}
}