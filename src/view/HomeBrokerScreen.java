/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import control.AccountController;
import control.AssetController;
import control.AssetNegotiationController;
import control.DateControl;
import control.OrderController;
import control.RelatesController;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import model.entities.Asset;
import model.entities.AssetNegotiation;
import model.entities.Order;
import model.entities.RelatesAccountAsset;
import model.enums.StateOrder;
import model.enums.TypeOrder;
import view.acess.AcessMyAssets;
import view.acess.AcessOrderBook;

/**
 *
 * @author pedro
 */
public final class HomeBrokerScreen extends javax.swing.JFrame {

    void listItems() {
        List<Asset> assets = AssetController.read();

        for (Asset asset : assets) {

            idComboBox.addItem(String.valueOf(asset.getId()));
        }
    }
    
    void setPriceAsset(){
        String priceAsset;
        Long idAsset = Long.valueOf(idComboBox.getSelectedItem().toString());
        
        AssetNegotiation lastNegotiation = AssetNegotiationController.getPriceAsset(idAsset);
        
        if(lastNegotiation != null){
            priceAsset = lastNegotiation.getValue().toString();
        } else {
            priceAsset = AssetController.search(idAsset).getInitialPrice().toString();
        }
        
        priceField.setText(priceAsset);
    }

    /**
     * Creates new form HomeBrokerScreen
     */
    public HomeBrokerScreen() {
        initComponents();

        amountField.setText("$ " + AccountController.current.getAmount());

        listItems();
        
        setPriceAsset();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amountField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        zeroButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        orderBookButton = new javax.swing.JButton();
        myAssetsButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        idComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        vascoToken = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        amountField.setEditable(false);

        jLabel1.setText("Amount");

        jLabel2.setText("HomeBroker");

        buyButton.setText("Buy");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        sellButton.setText("Sell");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        zeroButton.setText("Zero");
        zeroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zeroButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Order");

        orderBookButton.setText("Order Book");
        orderBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderBookButtonActionPerformed(evt);
            }
        });

        myAssetsButton.setText("My Assets");
        myAssetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myAssetsButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Asset");

        idComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idComboBoxItemStateChanged(evt);
            }
        });

        jLabel5.setText("Price Asset");

        priceField.setEditable(false);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/rivellino.png"))); // NOI18N

        vascoToken.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        vascoToken.setText("all rigths reserved to vascoToken");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(128, 128, 128)
                        .addComponent(vascoToken)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(jLabel3)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(148, 148, 148))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jLabel1)))
                                        .addContainerGap(102, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(buyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(zeroButton)
                                                .addGap(16, 16, 16)
                                                .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(57, 57, 57)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addGap(23, 23, 23))
                                                            .addComponent(idComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(orderBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(myAssetsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(17, 17, 17)
                                                        .addComponent(jLabel5)))))
                                        .addGap(45, 45, 45))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buyButton)
                            .addComponent(sellButton)
                            .addComponent(zeroButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderBookButton)
                            .addComponent(myAssetsButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(vascoToken)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed

        Long asset = Long.valueOf(idComboBox.getSelectedItem().toString());
        Long idRelated = RelatesController.requestId(AccountController.current.getId(), asset);

        BigDecimal value = new BigDecimal(JOptionPane.showInputDialog("Value :"));
        Integer quantity = Integer.valueOf(JOptionPane.showInputDialog("Quantity :"));
        BigDecimal totalValue = value.multiply(new BigDecimal(quantity));

        RelatesAccountAsset related = RelatesController.searchPerId(idRelated);
        
        if (related != null && related.getQuantity() >= quantity) {

            Order order = new Order();
            order.setAccount(AccountController.current.getId());
            order.setAsset(Long.valueOf(idComboBox.getSelectedItem().toString()));
            order.setType(TypeOrder.SELL);
            order.setState(StateOrder.OPEN);
            order.setValue(value);
            order.setQuantity(quantity);
            order.setTotalValue(totalValue);
            order.setStart(DateControl.now());

            if (OrderController.create(order)
                    && AccountController.fee(BigDecimal.TEN)) {
                JOptionPane.showMessageDialog(this, "Created Sucess!");
                
                if(OrderController.verifyOrderExecution(order)){
                    JOptionPane.showMessageDialog(this,"Executed Order !");
                    setPriceAsset();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Balance Insufficient !");
        }
        
        amountField.setText("$ " + AccountController.current.getAmount());
    }//GEN-LAST:event_sellButtonActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed

        BigDecimal value = new BigDecimal(JOptionPane.showInputDialog("Value :"));
        Integer quantity = Integer.valueOf(JOptionPane.showInputDialog("Quantity :"));
        BigDecimal totalValue = value.multiply(new BigDecimal(quantity));

        if (AccountController.hasBalance(totalValue.add(BigDecimal.TEN))) {
            Order order = new Order();
            order.setAccount(AccountController.current.getId());
            order.setAsset(Long.valueOf(idComboBox.getSelectedItem().toString()));
            order.setType(TypeOrder.BUY);
            order.setState(StateOrder.OPEN);
            order.setValue(value);
            order.setQuantity(quantity);
            order.setTotalValue(totalValue);
            order.setStart(DateControl.now());

            if (OrderController.create(order)
                    && AccountController.fee(BigDecimal.TEN)) {
                JOptionPane.showMessageDialog(this, "Created Sucess!");
                
                if(OrderController.verifyOrderExecution(order)){
                    JOptionPane.showMessageDialog(this,"Executed Order !");
                    setPriceAsset();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Balance Insufficient !");
        }

        amountField.setText("$ " + AccountController.current.getAmount());
    }//GEN-LAST:event_buyButtonActionPerformed

    private void zeroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroButtonActionPerformed

        if (AccountController.addOrderZero()) {

            Long asset = Long.valueOf(idComboBox.getSelectedItem().toString());

            Integer quantity = Integer.valueOf(JOptionPane.showInputDialog("Quantity :"));
            BigDecimal priceAsset = new BigDecimal(priceField.getText()).multiply(new BigDecimal(0.9));
            BigDecimal totalValue = priceAsset.multiply(new BigDecimal(quantity));

            if (AccountController.hasBalance(totalValue.add(BigDecimal.TEN)) && AssetController.hasAmount(asset, quantity)) {

                AssetNegotiation negotiation = new AssetNegotiation();
                negotiation.setAsset(asset);
                negotiation.setBuyer(AccountController.current.getId());
                negotiation.setSeller(AccountController.current.getId());
                negotiation.setQuantity(quantity);
                negotiation.setValue(priceAsset);
                negotiation.setValueTotal(totalValue);

                negotiation.setStart(DateControl.now());
                
                Long idRelates = RelatesController.requestId(AccountController.current.getId(), asset);

                if (idRelates != null) {
                    if (RelatesController.addAmount(idRelates, quantity)
                            && AccountController.withdraw(totalValue)
                            && AssetController.subAmount(asset, quantity)
                            && AssetNegotiationController.create(negotiation)) {
                        JOptionPane.showMessageDialog(this, "Add in Related !");
                    }
                } else {
                    RelatesAccountAsset related = new RelatesAccountAsset();
                    related.setAccount(AccountController.current.getId());
                    related.setAsset(asset);
                    related.setQuantity(quantity);

                    related.setStart(DateControl.now());

                    if (RelatesController.create(related)
                            && AccountController.fee(BigDecimal.TEN)
                            && AccountController.withdraw(totalValue)
                            && AssetController.subAmount(asset, quantity)
                            && AssetNegotiationController.create(negotiation)) {

                        JOptionPane.showMessageDialog(this, "Create Related !");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Balance Insufficient !");
            }

        } else {
            zeroButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Max of Order Zero Solicited !");
        }

        amountField.setText("$ " + AccountController.current.getAmount());
    }//GEN-LAST:event_zeroButtonActionPerformed

    private void idComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idComboBoxItemStateChanged
        setPriceAsset();
    }//GEN-LAST:event_idComboBoxItemStateChanged

    private void orderBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderBookButtonActionPerformed
        AcessOrderBook orderBookScreen = new AcessOrderBook();
        orderBookScreen.setVisible(true);
    }//GEN-LAST:event_orderBookButtonActionPerformed

    private void myAssetsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myAssetsButtonActionPerformed
        AcessMyAssets myAssetsScreen = new AcessMyAssets();
        myAssetsScreen.setVisible(true);
    }//GEN-LAST:event_myAssetsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HomeBrokerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeBrokerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeBrokerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeBrokerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeBrokerScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountField;
    private javax.swing.JButton backButton;
    private javax.swing.JButton buyButton;
    private javax.swing.JComboBox<String> idComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton myAssetsButton;
    private javax.swing.JButton orderBookButton;
    private javax.swing.JTextField priceField;
    private javax.swing.JButton sellButton;
    private javax.swing.JLabel vascoToken;
    private javax.swing.JButton zeroButton;
    // End of variables declaration//GEN-END:variables
}
