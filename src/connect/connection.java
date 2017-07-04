/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connect;

import database.ConnectToMySql;
import database.ConnectToOracle;
import database.ConnectToSqlServer;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class connection extends javax.swing.JFrame {
static String database_name ;
    static DefaultTableModel model ;   
 static String src_db ;
   static String dst_db ;
    public connection() {
     
        initComponents();
        Color mycolor=Color.decode("#F5F5F5");
         this.getContentPane().setBackground(mycolor); 
         this.jTable1.setBackground(mycolor);
         jPanel1.setBackground(mycolor);
      
        fii_available_data_base();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        editbtn = new javax.swing.JButton();
        Renamebtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        newbtn = new javax.swing.JButton();
        testDBtn = new javax.swing.JButton();
        testsbtn = new javax.swing.JButton();
        loginbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        testsbtn1 = new javax.swing.JButton();
        testsbtn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Destination");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Source");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 53, Short.MAX_VALUE))
        );

        editbtn.setBackground(new java.awt.Color(255, 255, 255));
        editbtn.setText("Edit");
        editbtn.setOpaque(false);
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });

        Renamebtn.setBackground(new java.awt.Color(255, 255, 255));
        Renamebtn.setText("Rename");
        Renamebtn.setOpaque(false);
        Renamebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RenamebtnActionPerformed(evt);
            }
        });

        deletebtn.setBackground(new java.awt.Color(255, 255, 255));
        deletebtn.setText("Delete");
        deletebtn.setAlignmentY(0.0F);
        deletebtn.setOpaque(false);
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        newbtn.setBackground(new java.awt.Color(255, 255, 255));
        newbtn.setText("New");
        newbtn.setAlignmentY(0.0F);
        newbtn.setFocusable(false);
        newbtn.setOpaque(false);
        newbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtnActionPerformed(evt);
            }
        });

        testDBtn.setBackground(new java.awt.Color(255, 255, 255));
        testDBtn.setText("Test Destination");
        testDBtn.setOpaque(false);
        testDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testDBtnActionPerformed(evt);
            }
        });

        testsbtn.setBackground(new java.awt.Color(255, 255, 255));
        testsbtn.setText("Select Destination");
        testsbtn.setBorderPainted(false);
        testsbtn.setOpaque(false);
        testsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsbtnActionPerformed(evt);
            }
        });

        loginbtn.setBackground(new java.awt.Color(255, 255, 255));
        loginbtn.setText("Login");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Avialable database"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        testsbtn1.setBackground(new java.awt.Color(255, 255, 255));
        testsbtn1.setText("Test Source");
        testsbtn1.setBorderPainted(false);
        testsbtn1.setOpaque(false);
        testsbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsbtn1ActionPerformed(evt);
            }
        });

        testsbtn2.setBackground(new java.awt.Color(255, 255, 255));
        testsbtn2.setText("Select Source");
        testsbtn2.setBorderPainted(false);
        testsbtn2.setOpaque(false);
        testsbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsbtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(testDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Renamebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testsbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(testsbtn2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(testsbtn)
                .addGap(18, 18, 18)
                .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newbtn)
                        .addGap(18, 18, 18)
                        .addComponent(editbtn)
                        .addGap(18, 18, 18)
                        .addComponent(Renamebtn)
                        .addGap(18, 18, 18)
                        .addComponent(deletebtn)
                        .addGap(18, 18, 18)
                        .addComponent(testsbtn1)
                        .addGap(18, 18, 18)
                        .addComponent(testDBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testsbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtnActionPerformed
Add ad = new Add();
ad.setVisible(true);
      
    }//GEN-LAST:event_newbtnActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
 try{
        int x = jTable1.getSelectedRow();
        database_name = jTable1.getValueAt(x, 0).toString();
        Edit ed = new Edit();
        ed.setVisible(true);
 }
 catch(Exception e){
  JOptionPane.showMessageDialog(new JPanel(), "please choose database you want to edit it ", "Error", JOptionPane.ERROR_MESSAGE);
 }
    }//GEN-LAST:event_editbtnActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void RenamebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenamebtnActionPerformed
        try {
            int x = jTable1.getSelectedRow();

            database_name = jTable1.getValueAt(x, 0).toString();

            rename rename_file = new rename();
            rename_file.setVisible(true);

        } catch (java.lang.ArrayIndexOutOfBoundsException es) {
            JOptionPane.showMessageDialog(new JPanel(), "please choose database you want to rename it ", "Error", JOptionPane.ERROR_MESSAGE);

        }
// TODO add your handling code here:
    }//GEN-LAST:event_RenamebtnActionPerformed

    private void testsbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsbtn2ActionPerformed
    try{
        
        String dest ;
        int x =jTable1.getSelectedRow();
       String w = jTable1.getValueAt(x, 0).toString();
      jTextField2.setText(w);
   
        File localFile = new File( "connection.properties");
            Properties localProperties = new Properties();
    FileInputStream localFileInputStream = null;
          localFileInputStream = new FileInputStream(localFile);
      localProperties.load(localFileInputStream);
dest=localProperties.getProperty("destination");
  
Properties p= new Properties(); 
//filling the properties data structure with the url/username/password
p.setProperty("source", w); 
p.setProperty("destination", dest); 


      File localFile2 = new File( "connection.properties");// creating the .properties file based on the given name to the db
FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
p.store(localFileOutputStream, null);

      src_db=jTextField2.getText() ;
      Main_page.jTable1.setValueAt(src_db, 0, 1);
      
      
    }
    catch(Exception es){
    
        System.out.println(es);
    }
        
        
    }//GEN-LAST:event_testsbtn2ActionPerformed

    private void testsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsbtnActionPerformed
        try{  
            String source ;
        int x =jTable1.getSelectedRow();
       String w = jTable1.getValueAt(x, 0).toString();
      jTextField1.setText(w);
       File localFile = new File( "connection.properties");
            Properties localProperties = new Properties();
    FileInputStream localFileInputStream = null;
          localFileInputStream = new FileInputStream(localFile);
      localProperties.load(localFileInputStream);
source=localProperties.getProperty("source");
  
Properties p= new Properties(); 
//filling the properties data structure with the url/username/password
p.setProperty("source", source); 
p.setProperty("destination", w); 


      File localFile2 = new File( "connection.properties");// creating the .properties file based on the given name to the db
FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
p.store(localFileOutputStream, null);
 dst_db=jTextField1.getText();
 Main_page.jTable1.setValueAt(dst_db, 1, 1);
        }
        catch(Exception es) {
        
            System.out.println(es);
        
        }
    }//GEN-LAST:event_testsbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
      try{         int x =jTable1.getSelectedRow();
       String w = jTable1.getValueAt(x, 0).toString();
        
        File localFile = new File("dot properties files"+ "\\" + w +  ".properties");
localFile.delete();
  Path path = Paths.get("names of databases.txt");
Charset charset = StandardCharsets.UTF_8;

String content = new String(Files.readAllBytes(path), charset);
content = content.replaceAll(w, "");
Files.write(path, content.getBytes(charset));
this.dispose();
//JOptionPane.showMessageDialog(null,"database deleted","Success", 2 , new ImageIcon("Ok.png"));  
connection ma = new connection();
ma.setVisible(true);

      }
      catch(Exception e){
      System.out.println(e);
      }
      

    }//GEN-LAST:event_deletebtnActionPerformed

    private void testsbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsbtn1ActionPerformed
try{
        int x =jTable1.getSelectedRow();
       String w = jTable1.getValueAt(x, 0).toString();
              String folder= "dot properties files";  
      
        
        File localFile = new File(folder + "\\" +w+  ".properties");
            Properties localProperties = new Properties();
    FileInputStream localFileInputStream = null;
          localFileInputStream = new FileInputStream(localFile);
      localProperties.load(localFileInputStream);
  System.out.println(localProperties.getProperty("dbType"));
      
   if(localProperties.getProperty("dbType").matches("Oracle"))   {
      
    Connection conn= null;  
System.out.println(localProperties.getProperty("dbURL"));

ConnectToOracle c= new ConnectToOracle(localProperties.getProperty("dbURL"),localProperties.getProperty("username"), localProperties.getProperty("password"));

conn= c.returnConnection(); 
if(conn==null){
    JOptionPane.showMessageDialog(null,"error in connection ","error", 2 , new ImageIcon("Ok.png"));
    

}
else {
 JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
}
   
}
   else if (localProperties.getProperty("dbType").matches("sqlServer" )){
           Connection conn= null;  
System.out.println(localProperties.getProperty("dbURL"));

ConnectToSqlServer  c= new ConnectToSqlServer(localProperties.getProperty("dbURL"),localProperties.getProperty("username"), localProperties.getProperty("password"));

conn= c.returnConnection(); 
if(conn==null){
    JOptionPane.showMessageDialog(null,"error in connection ","error", 2 , new ImageIcon("Ok.png"));
    

}
else {
 JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
}
       
   
   
   
   }
   else if (localProperties.getProperty("dbType").matches("mySql")){
       Connection conn= null;  


ConnectToMySql c= new ConnectToMySql(localProperties.getProperty("dbURL"),localProperties.getProperty("username"), localProperties.getProperty("password"));

conn= c.returnConnection(); 
if(conn==null){
    JOptionPane.showMessageDialog(null,"error in connection ","error", 2 , new ImageIcon("Ok.png"));
}
else {
 JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
}
   
   
   }
   else {
   JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
   }
   
}
catch(Exception es){

    System.out.println(es);}
    }//GEN-LAST:event_testsbtn1ActionPerformed

    private void testDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testDBtnActionPerformed
try{
        int x =jTable1.getSelectedRow();
       String w = jTable1.getValueAt(x, 0).toString();
              String folder= "dot properties files";  
      
        
        File localFile = new File(folder + "\\" +w+  ".properties");
            Properties localProperties = new Properties();
    FileInputStream localFileInputStream = null;
          localFileInputStream = new FileInputStream(localFile);
      localProperties.load(localFileInputStream);
  System.out.println(localProperties.getProperty("dbType"));
      
   if(localProperties.getProperty("dbType").matches("Oracle"))   {
      
    Connection conn= null;  


ConnectToOracle c= new ConnectToOracle(localProperties.getProperty("dbURL"),localProperties.getProperty("username"), localProperties.getProperty("password"));

conn= c.returnConnection(); 
if(conn==null){
    JOptionPane.showMessageDialog(null,"error in connection ","error", 2 , new ImageIcon("Ok.png"));
    

}
else {
 JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
}
   
}
   else if (localProperties.getProperty("dbType").matches("mySql")){
       Connection conn= null;  


ConnectToMySql c= new ConnectToMySql(localProperties.getProperty("dbURL"),localProperties.getProperty("username"), localProperties.getProperty("password"));

conn= c.returnConnection(); 
if(conn==null){
    JOptionPane.showMessageDialog(null,"error in connection ","error", 2 , new ImageIcon("Ok.png"));
}
else {
 JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
}
   }
   else {
   JOptionPane.showMessageDialog(null,"database connected","Success", 2 , new ImageIcon("Ok.png"));
   }
}
catch(Exception es){

    System.out.println(es);}

    }//GEN-LAST:event_testDBtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
   Main_page ma = null;
    try {
        ma = new Main_page();
    } catch (SQLException ex) {
        Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
    }
   ma.setVisible(true);
   this.dispose();
    }//GEN-LAST:event_loginbtnActionPerformed
public static void fii_available_data_base(){
    try{
        File file = new File("names of databases.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Object st[]=new Object[2] ;;
 while( (st[0] = br.readLine()) != null) {
model = (DefaultTableModel) jTable1.getModel() ;
     if (st[0].equals("")) {
         
  
     }
     else {
     model.addRow(st);
     }

    }
    }
    catch (Exception es){
        System.out.println(es);
    
    }


}
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new connection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Renamebtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton loginbtn;
    private javax.swing.JButton newbtn;
    private javax.swing.JButton testDBtn;
    private javax.swing.JButton testsbtn;
    private javax.swing.JButton testsbtn1;
    private javax.swing.JButton testsbtn2;
    // End of variables declaration//GEN-END:variables
}
