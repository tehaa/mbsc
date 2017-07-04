/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genertation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;


public class main_frame extends javax.swing.JFrame {

    /**
     * Creates new form main_frame
     */
    public main_frame() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        Color mycolor = Color.decode("#BDBDBD");
        this.getContentPane().setBackground(mycolor);
        intail_com();
    }
    
    public static void intail_com(){
        Color mycolor = Color.decode("#9E9E9E");
        add_btn.setBackground(mycolor);
        add_btn.setFont(new Font("Ubuntu", 0, 20));
        add_btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add_btn.setBorderPainted(false);
        add_btn.setFocusPainted(false);
        edit_btn.setBackground(mycolor);
        edit_btn.setFont(new Font("Ubuntu", 0, 20));
        edit_btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_btn.setBorderPainted(false);
        edit_btn.setFocusPainted(false);
        gen_btn.setBackground(mycolor);
        gen_btn.setFont(new Font("Ubuntu", 0, 20));
        gen_btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gen_btn.setBorderPainted(false);
        gen_btn.setFocusPainted(false);
        map_btn.setBackground(mycolor);
        map_btn.setFont(new Font("Ubuntu", 0, 20));
        map_btn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        map_btn.setBorderPainted(false);
        map_btn.setFocusPainted(false);

    }
    
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add_btn = new javax.swing.JButton();
        edit_btn = new javax.swing.JButton();
        gen_btn = new javax.swing.JButton();
        map_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        add_btn.setText("Add Report");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        edit_btn.setText("Edit Report");
        edit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btnActionPerformed(evt);
            }
        });

        gen_btn.setText("Generate Report");
        gen_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_btnActionPerformed(evt);
            }
        });

        map_btn.setText("Mapping");
        map_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(edit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gen_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
            .addComponent(map_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(edit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(map_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(gen_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
     add_report add= new add_report();
     add.setVisible(true);
    }//GEN-LAST:event_add_btnActionPerformed

    private void gen_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_btnActionPerformed
     Generate_report gen =new Generate_report();
     gen.setVisible(true);
    }//GEN-LAST:event_gen_btnActionPerformed

    private void edit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btnActionPerformed
edit_report ed =new edit_report();
ed.setVisible(true);
    }//GEN-LAST:event_edit_btnActionPerformed

    private void map_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map_btnActionPerformed
 upload_gl_mapping upload = new upload_gl_mapping();
     upload.setVisible(true );        // TODO add your handling code here:
    }//GEN-LAST:event_map_btnActionPerformed

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
            java.util.logging.Logger.getLogger(main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton add_btn;
    private static javax.swing.JButton edit_btn;
    private static javax.swing.JButton gen_btn;
    private static javax.swing.JButton map_btn;
    // End of variables declaration//GEN-END:variables
}
