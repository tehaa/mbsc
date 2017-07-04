/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genertation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ahmed
 */
public class param extends javax.swing.JFrame {

    static DefaultTableModel model;
    static Connection c;
    static Statement stmt ;
    

    public param() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        Color mycolor = Color.decode("#EEEEEE");
        this.setTitle("Generate Report");
        this.getContentPane().setBackground(mycolor);
        jPanel1.setBackground(mycolor);
        jPanel2.setBackground(mycolor);
        int[] columnsWidth = {
            50, 400, 500
        };

        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = jTable2.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }

        fill_table();

    }

    public static void add_param() {
        try {
                String x =  connect.Main_page.dest_url;
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(x, "root", null);
           String query = "delete from parameters_table where Report_No= "+Generate_report.report_name;
            Statement st = c.createStatement();
            st.executeUpdate(query);
            PreparedStatement localPreparedStatement = c.prepareStatement("insert into parameters_table (item,parameters,Report_No,report_idx,number,calcus,rangee) values (?,?,?,?,?,?,?)");
            int count = 1;
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(jTable2.getValueAt(i, 2).toString().split(",")));
                for (int j = 0; j < myList.size(); j++) {

                    localPreparedStatement.setString(1, jTable2.getValueAt(i, 1).toString());
                    localPreparedStatement.setString(2, myList.get(j));
                    localPreparedStatement.setString(3, Generate_report.report_name);
                    localPreparedStatement.setInt(4, count);
                    localPreparedStatement.setString(5, jTable2.getValueAt(i, 3).toString());
                    localPreparedStatement.setString(6, jTable2.getValueAt(i, 4).toString());
                      localPreparedStatement.setString(7, jTable2.getValueAt(i, 5).toString());
                    localPreparedStatement.addBatch();
                }
                count++;
            }
            localPreparedStatement.executeBatch();
              JOptionPane.showMessageDialog(null, "Done", "Success", 2, new ImageIcon("Ok.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void fill_table() {
        try {
            Generate.intail_connection();
            Object[] data = new Object[6];
            c = Generate.c;
            stmt = Generate.stmt;
            Statement stmt2 ;
            stmt2=stmt ;
            String query = "SELECT distinct item   from parameters_table where `Report_No`="+Generate_report.report_name+";";
            System.out.println(query);
            ResultSet rset = c.createStatement().executeQuery(query);
            while (rset.next()) {
                data[1] = rset.getString("item");
              
                 System.out.println(data[1]);
                String param = "";
                String sel_param = "select `parameters` from parameters_table where item ='" + data[1] + "'";
               
                ResultSet rset2 = c.createStatement().executeQuery(sel_param);
                while (rset2.next()) {                    
                  param=param+rset2.getString("parameters")+",";
                }
                param= param.substring(0, param.length()-1);
                data[2]=param ;
                System.out.println(param.substring(param.lastIndexOf(param)));
                param="";
                String sel_index="select distinct report_idx from parameters_table where item='"+data[1] +"';";
                ResultSet rset3 = c.createStatement().executeQuery(sel_index);
                while (rset3.next()) {                    
           data[0]=rset3.getString("report_idx");
                }
                 String sel_number="select distinct number from parameters_table where item='"+data[1] +"';";
                 System.out.println(sel_number);
                ResultSet rset4 = c.createStatement().executeQuery(sel_number);
                while (rset4.next()) {                    
           data[3]=rset4.getString("number");
                }
                  String sel_calcus="select distinct calcus from parameters_table where item='"+data[1] +"';";
               
                ResultSet rset5 = c.createStatement().executeQuery(sel_calcus);
                while (rset5.next()) {                    
           data[4]=rset5.getString("calcus");
                }
                  String sel_range="select distinct rangee from parameters_table where item='"+data[1] +"';";
                System.out.println(sel_range);
                ResultSet rset6 = c.createStatement().executeQuery(sel_range);
                while (rset6.next()) {                    
           data[5]=rset6.getString("rangee");
                }
                
                
               model =(DefaultTableModel) jTable2.getModel() ;
               model.addRow(data);
            }
        } catch (Exception es) {
            System.out.println(es);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Parameters");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item", "Paramter", "Number", "Calculations", "Range"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setInheritsPopupMenu(true);
        jTable2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jTable2.setName("set 1800 paramter"); // NOI18N
        jTable2.setRowHeight(32);
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("save");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("add paramter field");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("remove field");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        add_param();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int x = jTable2.getRowCount();
model=(DefaultTableModel) jTable2.getModel();
model.setRowCount(x+1);
jTable2.setValueAt(x+1, x, 0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int x = jTable2.getSelectedRow();
        System.out.println(x);
model=(DefaultTableModel) jTable2.getModel();
model.removeRow(x);    
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(param.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(param.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(param.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(param.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new param().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
