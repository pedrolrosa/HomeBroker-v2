/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.asset;

import com.itextpdf.text.DocumentException;
import control.AssetController;
import java.io.IOException;

/**
 *
 * @author pedro
 */
public class AssetMenu extends javax.swing.JFrame {

    /**
     * Creates new form AssetMenu
     */
    public AssetMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createUserButton = new javax.swing.JButton();
        readUserButton = new javax.swing.JButton();
        updateUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vascoToken = new javax.swing.JLabel();
        reportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createUserButton.setText("Create Asset");
        createUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserButtonActionPerformed(evt);
            }
        });

        readUserButton.setText("Read Assets");
        readUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readUserButtonActionPerformed(evt);
            }
        });

        updateUserButton.setText("Update Asset");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setText("Delete Asset");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Asset Menu");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/basilio.png"))); // NOI18N

        vascoToken.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        vascoToken.setText("all rigths reserved to vascoToken");

        reportButton.setText("Report");
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(createUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(readUserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateUserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(reportButton)
                                    .addComponent(exitButton))))
                        .addGap(0, 78, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vascoToken)
                .addGap(221, 221, 221))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(createUserButton)
                        .addGap(27, 27, 27)
                        .addComponent(readUserButton)
                        .addGap(27, 27, 27)
                        .addComponent(updateUserButton)
                        .addGap(30, 30, 30)
                        .addComponent(deleteUserButton)
                        .addGap(18, 18, 18)
                        .addComponent(reportButton)
                        .addGap(24, 24, 24)
                        .addComponent(exitButton))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(vascoToken)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        AssetCreate assetCreate = new AssetCreate();
        assetCreate.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_createUserButtonActionPerformed

    private void readUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readUserButtonActionPerformed
        AssetRead assetRead = new AssetRead();
        assetRead.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_readUserButtonActionPerformed

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        AssetDelete assetDelete = new AssetDelete();
        assetDelete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed

        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        try {

            AssetController.generatePDF();
        } catch (DocumentException de) {
            System.out.println("Erro ao gerar pdf");
        } catch (IOException IE) {
            System.out.println("Erro ao gerar pdf");
        }
    }//GEN-LAST:event_reportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AssetMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssetMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssetMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssetMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssetMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createUserButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton readUserButton;
    private javax.swing.JButton reportButton;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JLabel vascoToken;
    // End of variables declaration//GEN-END:variables
}
