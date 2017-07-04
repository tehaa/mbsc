
package genertation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class add_details_report {
    static boolean  state=true ;
    public static void copyFileUsingChannel(File source, File dest) throws IOException {
    FileChannel sourceChannel = null;
    FileChannel destChannel = null;
    try {
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
    } finally {
        sourceChannel.close();
        destChannel.close();
    }
}
 public  static void genrate_mapping(){
try{
      File f = new File("G LINE Mapping\\"+add_report.r_name+".xls");

                WritableWorkbook w = Workbook.createWorkbook(f);
                WritableSheet s = w.createSheet("Sheet 1", 0);
                s.addCell(new Label(0, 0, ""));
                w.write();
                w.close();
}
catch(Exception es){
    System.out.println(es);
}
}
 public static void add_file_details(String[] details) { 


String folder= "reports";


//saving in the properties file. 
File dir= new File(folder); //creating the folder in the current directory
dir.mkdir(); 
Properties p= new Properties(); 
//filling the properties data structure with the url/username/password
p.setProperty("name", details[0]); 
p.setProperty("type", details[1]); 
p.setProperty("mapping", details[2]); 
p.setProperty("script", details[3]); 
p.setProperty("path", details[4]);
      File localFile = new File(folder + "\\" + details[0] +  ".properties"); // creating the .properties file based on the given name to the db
if(localFile.exists()) {
  JOptionPane.showMessageDialog(null,"report is exist ","Success", 2 , new ImageIcon("Ok.png"));
state=false ;
    System.out.println(state);
}
else {
try{
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
 public static void addreport(String name) { 
File file= new File("reports.txt");
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
    public static void add() {

        try {
            String[] details = new String[5];
            String[] file_name = null;
            details[0] = add_report.r_name;
            details[1] = add_report.type;
            details[2] = add_report.map_path;
            details[3] = add_report.scr_path;
            if (add_report.map_path.matches("")) {
                details[2] = " G LINE Mapping\\" + add_report.r_name + ".xls";

            }
            if (details[1].matches("CBE GL Report")) {
                genrate_mapping();
            }
            file_name = add_report.path.split("\\.");
            File source = new File(add_report.path);
            details[4] = source.toString();
            add_file_details(details);
            if (state) {
                addreport(add_report.r_name);
                JOptionPane.showMessageDialog(null, "Done", "Success", 2, new ImageIcon("Ok.png"));
            }

        } catch (Exception es) {
            System.out.println(es);
        }

    }
}
