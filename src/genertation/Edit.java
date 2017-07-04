
package genertation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class Edit {

    public static ArrayList<String> reports = new ArrayList<String>();

    public static void fii_combo() {
        try {
            reports.clear();
            File file = new File("reports.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object st[] = new Object[2];;
            while ((st[0] = br.readLine()) != null) {

                if (st[0].equals("")) {
                } else {
                    reports.add(st[0].toString());
                }

            }
        } catch (Exception es) {
            System.out.println(es);

        }

    }
    public static void filldata( String name){

        try {

            String folder = "reports";

            File localFile = new File(folder + "\\" + name + ".properties");
            Properties localProperties = new Properties();
            FileInputStream localFileInputStream = null;
            localFileInputStream = new FileInputStream(localFile);
            localProperties.load(localFileInputStream);

            edit_report.r_name = localProperties.getProperty("name");
            edit_report.map_path = localProperties.getProperty("mapping");
            edit_report.path = localProperties.getProperty("path");
            edit_report.type = localProperties.getProperty("type");
            edit_report.scr_path = localProperties.getProperty("script");


     }
     catch(Exception es){
         System.out.println(es);
     }
    }
    public static void edit() {
        try {
            String folder = "reports";
            Properties p = new Properties();
//filling the properties data structure with the url/username/password
            p.setProperty("name", edit_report.r_name2);
            p.setProperty("mapping", edit_report.map_path);
            p.setProperty("path", edit_report.path);
            p.setProperty("script", edit_report.scr_path);
            File localFile = new File(folder + "\\" + edit_report.r_name2 + ".properties"); // creating the .properties file based on the given name to the db
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
            p.store(localFileOutputStream, null);
        } catch (Exception es) {
            System.out.println(es);
        }

    }
public static void edit_file_name(){
    try{
 String folder= "reports";     
    
    
  File localFile = new File(folder + "\\" + edit_report.r_name+  ".properties"); // creating the .properties file based on the given name to the db
if(localFile.exists()) {
 File f1 = null;
  f1 = new File(folder + "\\" +edit_report.r_name2 +".properties");
  localFile.renameTo(f1);
    System.out.println("file name  changed");

}

}
    catch(Exception es){
        System.out.println(es);
        }
}
public static void edit_report_name(){
    try{
    Path path = Paths.get("reports.txt");
Charset charset = StandardCharsets.UTF_8;

String content = new String(Files.readAllBytes(path), charset);
content = content.replaceAll(edit_report.r_name,edit_report.r_name2);
Files.write(path, content.getBytes(charset));
    }
    catch(Exception es){
    
        System.out.println(es);
    }

}
}
