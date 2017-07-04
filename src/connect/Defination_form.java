/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connect;

import static connect.fielddef.srcConn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Defination_form extends javax.swing.JFrame {

    static Connection srcConn;
    static Connection dstConn;
    static String appname;
    static DefaultTableModel model,model2;
    
    public Defination_form() throws SQLException {
        initComponents();
        this.setTitle("CBE Reports");
        Color mycolor = Color.decode("#C5CAE9");
        this.getContentPane().setBackground(mycolor);

        this.jPanel1.setBackground(mycolor);
        Color mycolor2 = Color.decode("#C5CAE9");
        this.jPanel2.setBackground(mycolor2);
        this.jPanel3.setBackground(mycolor2);
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setBounds(0,0,screenSize.width, screenSize.height);
  

    }
  
    public static void refreshtable() {

        model=(DefaultTableModel) jTable1.getModel();
                 Object[] row = new Object[15] ;
        
       try{   srcConn = Main_page.srcConn; 
                    dstConn = Main_page.dstConn;
        Statement localStatement = Defination_form.dstConn.createStatement();
      ResultSet localResultSet = localStatement.executeQuery("SELECT t24_field_name,report_field_name,field_type,field_location,field_multi_location,field_sub_location,field_size,decimal_size,field_data_type,field_group,is_primary,is_id,is_multi,is_sub,is_index FROM import_fields_list2 WHERE t24_name='"+appname+"';");
      while(localResultSet.next()){
          row[0]=localResultSet.getString(1);
          row[1]=localResultSet.getString(2);   
   row[2]=localResultSet.getString(3);  
   row[3]=localResultSet.getInt(4);  
      row[4]=localResultSet.getInt(5);  
         row[5]=localResultSet.getInt(6);
             row[6]=localResultSet.getInt(7);  
         row[7]=localResultSet.getInt(8);
                 row[8]=localResultSet.getString(9);
          row[9]=localResultSet.getString(10);  
           row[10]=localResultSet.getBoolean(11);
           row[11]=localResultSet.getBoolean(12);
           row[12]=localResultSet.getBoolean(13);
     row[13]=localResultSet.getBoolean(14);
     row[14]=localResultSet.getBoolean(15);
  if((boolean)row[10]){
  
  row[14]=true ;
  }
              
             model = (DefaultTableModel) jTable1.getModel() ;
                  model.addRow(row);
        
      }
    
      
       }
       catch(Exception es){
           System.out.println(es);
       
       }
    }
    
    public static void  refresh_table2() {
            
       // model=(DefaultTableModel) jTable2.getModel();
                 Object[] row = new Object[15] ;
        
       try{
             srcConn = Main_page.srcConn; 
                    dstConn = Main_page.dstConn;
        Statement localStatement = Defination_form.dstConn.createStatement();
        
      ResultSet localResultSet = localStatement.executeQuery("SELECT t24_field_name,report_field_name,field_type,field_location,field_multi_location,field_sub_location,field_size,decimal_size,field_data_type,field_group,is_primary,is_id,is_multi,is_sub,is_index FROM import_fields_list WHERE t24_name='"+appname+"';");
      while(localResultSet.next()){
          row[0]=localResultSet.getString(1);
          row[1]=localResultSet.getString(2);   
   row[2]=localResultSet.getString(3);  
   row[3]=localResultSet.getInt(4);  
      row[4]=localResultSet.getInt(5);  
         row[5]=localResultSet.getInt(6);
             row[6]=localResultSet.getInt(7);  
         row[7]=localResultSet.getInt(8);
                 row[8]=localResultSet.getString(9);
          row[9]=localResultSet.getString(10);  
           row[10]=localResultSet.getBoolean(11);
           row[11]=localResultSet.getBoolean(12);
           row[12]=localResultSet.getBoolean(13);
     row[13]=localResultSet.getBoolean(14);
    
  
              
              model2=(DefaultTableModel) jTable2.getModel();
                  model2.addRow(row);
        
      }
       }
    catch(Exception es){
            
            System.out.println(es);
            }
    
    }
    
    
    
    
 public   void setAppName(String paramString)
  {
   appname = paramString;
      System.out.println(appname);

 
  }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Create fields");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "T24 field name", "Report field name", "field Type", "field Location", "Field Multi location", "field Sub location", "feild size ", "Decimal size", "Data type", "Field group", "is primary", "is id", "is multi", "is sub", "is selected"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 51));
        jButton2.setText("select");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setForeground(new java.awt.Color(0, 0, 153));
        jButton4.setText("Mark All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setForeground(new java.awt.Color(0, 0, 153));
        jButton5.setText("UnMark all");
        jButton5.setFocusCycleRoot(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "T24 field name", "Report field name ", "field type", "field location ", "field multi location", "field sub location ", "field size", "Decimal size", "Data type", "field group", "is primary", "is id", "is multi", "is sub"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 153));
        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setForeground(new java.awt.Color(0, 0, 153));
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String x =jTextField1.getText();
       int number_of_row=jTable1.getRowCount();
       int getrow = 0 ;
        for (int i = 0; i < number_of_row; i++) {
    
            Object field_name= jTable1.getValueAt(i, 0);
            System.out.println(field_name.toString());
            if(x.matches(field_name.toString())){
       getrow=i ;
            }
        }
        jTable1.setRowSelectionInterval(getrow, getrow);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(true, i, 14);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        boolean selected;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            selected = (boolean) jTable1.getValueAt(i, 14);
            if (selected) {
                Object[] row = new Object[14];
                row[0] = jTable1.getValueAt(i, 0);
                row[1] = jTable1.getValueAt(i, 1);
                row[2] = jTable1.getValueAt(i, 2);
                row[3] = jTable1.getValueAt(i, 3);
                row[4] = jTable1.getValueAt(i, 4);
                row[5] = jTable1.getValueAt(i, 5);
                row[6] = jTable1.getValueAt(i, 6);
                row[7] = jTable1.getValueAt(i, 7);
                row[8] = jTable1.getValueAt(i, 8);
                row[9] = jTable1.getValueAt(i, 9);
                row[10] = jTable1.getValueAt(i, 10);
                row[11] = jTable1.getValueAt(i, 11);
                row[12] = jTable1.getValueAt(i, 12);
                row[13] = jTable1.getValueAt(i, 13);

                model = (DefaultTableModel) jTable2.getModel();
                model.addRow(row);

            }
  
        }
   
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try{ 
             for (int i = 0; i < jTable2.getRowCount(); i++) {
            for (int j = i+1; j < jTable2.getRowCount(); j++) {
                if (jTable2.getValueAt(i, 0).toString().toUpperCase().matches(jTable2.getValueAt(j, 0).toString().toUpperCase())) {
          model=(DefaultTableModel) jTable2.getModel();
          model.removeRow(j);
                }
        
                
            }
            
        }
             PreparedStatement localPreparedStatement2 = this.dstConn.prepareStatement("delete  FROM import_fields_list where t24_name = '"+appname+"';");
             localPreparedStatement2.executeUpdate();
           
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
          Date d = new Date() ;
          String X =dateFormat.format(d);
        
          int col_order = 1;
            PreparedStatement localPreparedStatement = this.dstConn.prepareStatement("insert into import_fields_list (t24_name,col_order,t24_field_name,report_field_name,field_type,field_location,field_multi_location,field_sub_location,field_size,field_group,is_primary,is_id,is_multi,is_sub)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            

        for (int i = 0; i < jTable2.getRowCount(); i++) {
             try {
localPreparedStatement.setString(1, appname);
localPreparedStatement.setInt(2, col_order);
localPreparedStatement.setString(3,jTable2.getValueAt(i, 0).toString());
localPreparedStatement.setString(4, jTable2.getValueAt(i, 1).toString());
localPreparedStatement.setString(5, jTable2.getValueAt(i, 2).toString());
localPreparedStatement.setInt(6,Integer.parseInt(jTable2.getValueAt(i, 3).toString()));
localPreparedStatement.setInt(7,Integer.parseInt(jTable2.getValueAt(i, 4).toString()));
localPreparedStatement.setInt(8,Integer.parseInt(jTable2.getValueAt(i, 5).toString()));
localPreparedStatement.setInt(9,Integer.parseInt(jTable2.getValueAt(i, 6).toString()));
localPreparedStatement.setString(10,null);
localPreparedStatement.setBoolean(11,(boolean)jTable2.getValueAt(i, 10));
localPreparedStatement.setBoolean(12,(boolean)jTable2.getValueAt(i, 11));
localPreparedStatement.setBoolean(13,(boolean)jTable2.getValueAt(i, 12));
localPreparedStatement.setBoolean(14,(boolean)jTable2.getValueAt(i, 13));
col_order ++ ;
            }
            
            catch (SQLException ex) {
                Logger.getLogger(Defination_form.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           localPreparedStatement.addBatch(); 

        }
          

      localPreparedStatement.executeBatch();
      String query="update import_list set last_update=null where app_name='"+appname+"' ;";
           
       PreparedStatement localPreparedStatement3 = this.dstConn.prepareStatement("update import_list set last_update=null where app_name='"+appname+"';");
             localPreparedStatement3.executeUpdate();
       model = (DefaultTableModel) jTable2.getModel() ;
           System.out.println(model.getRowCount());
              model.setRowCount(0);
               model = (DefaultTableModel) jTable1.getModel() ;
                System.out.println(model.getRowCount());
             model.setRowCount(0);
           JOptionPane.showMessageDialog(null,"Done","Success", 2 , new ImageIcon("Ok.png"));      
          
          
       }
       catch(Exception  es){
               System.out.println(es);

       
       }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(false, i, 14);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            int  s = jTable2.getSelectedRow() ;
                model = (DefaultTableModel) jTable2.getModel() ;
        if(s>-1)
        {
         model.removeRow(s);
         
        }
            else 
            JOptionPane.showMessageDialog(null, "برجاء تحديد العنصر المراد حذفه");
        
     
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Defination_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Defination_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Defination_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Defination_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Defination_form().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Defination_form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
