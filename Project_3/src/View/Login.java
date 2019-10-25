/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import SocketsImpl.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Marco Gamboa
 */
public class Login extends javax.swing.JFrame {

    List<JButton> btnChars;
    HashMap<String, ExtendedDefaultCharacter> selectedChars;
    HashMap<String, ExtendedDefaultCharacter> Chars;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        btnChars = new ArrayList<>();
        selectedChars = new HashMap();
        Chars = new HashMap();
        this.setResizable(false);
        setCharacters();

    }

    public void setCharacters() {
        List<ExtendedDefaultCharacter> chars;
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton();
            button.setName("CHARACTER#" + i);
            button.setBorder(new EtchedBorder());
            button.setPreferredSize(new Dimension(140, 210));
            JLabel lbl = new JLabel("CHARACTER#" + i);
            lbl.setForeground(Color.white);
            button.add(lbl);
            button.setActionCommand("CHARACTER#" + i);
            button.setBackground(Color.black);
            ImageIcon imageIcon = new ImageIcon("death" + i + ".png");
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(140, 210, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way             
            button.setIcon(new ImageIcon(newimg));
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectChar(e.getActionCommand());
                }
            };
            button.addActionListener(actionListener);

            CharPane.add(button);
            btnChars.add(button);
        }
        CharPane.revalidate();
        CharPane.repaint();
    }

    private void selectChar(String name) {
        TitledBorder border = new TitledBorder("SELECTED");
        border.setTitleColor(Color.WHITE);

        for (JButton button : btnChars) {
            if (button.getName().equals(name)) {
                if (selectedChars.containsKey(name)) {
                    button.setBorder(new EtchedBorder());
                    selectedChars.remove(name);
                } else {
                    if(selectedChars.size()<=5){
                        button.setBorder(border);
                        selectedChars.put(name, Chars.get(name));
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txfNickname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txfServer = new javax.swing.JTextField();
        btnPlay = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txfRival = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CharPane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        txfNickname.setBackground(new java.awt.Color(0, 0, 0));
        txfNickname.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txfNickname.setForeground(new java.awt.Color(0, 153, 0));
        txfNickname.setText("nickname");
        txfNickname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfNicknameActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 51));
        jLabel1.setText("NICKNAME:");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 51));
        jLabel2.setText("HOST:");

        txfServer.setBackground(new java.awt.Color(0, 0, 0));
        txfServer.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txfServer.setForeground(new java.awt.Color(0, 153, 0));
        txfServer.setText("192.168.1.1");
        txfServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfServerActionPerformed(evt);
            }
        });

        btnPlay.setBackground(new java.awt.Color(0, 0, 0));
        btnPlay.setForeground(new java.awt.Color(0, 153, 0));
        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 51));
        jLabel3.setText("PORT:");

        txfRival.setBackground(new java.awt.Color(0, 0, 0));
        txfRival.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txfRival.setForeground(new java.awt.Color(0, 153, 0));
        txfRival.setText("8000");
        txfRival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfRivalActionPerformed(evt);
            }
        });

        CharPane.setBackground(new java.awt.Color(51, 51, 51));
        CharPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHARACTERS", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 153, 51))); // NOI18N
        jScrollPane1.setViewportView(CharPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfRival))
                            .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txfRival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        ActionWindow aw = new ActionWindow();
        try {
            aw.setPlayer(new Player(txfNickname.getText(),new ArrayList<>(Chars.values())));
            
            aw.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnPlayActionPerformed

    private void txfNicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfNicknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfNicknameActionPerformed

    private void txfServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfServerActionPerformed

    private void txfRivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfRivalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfRivalActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CharPane;
    private javax.swing.JButton btnPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txfNickname;
    private javax.swing.JTextField txfRival;
    private javax.swing.JTextField txfServer;
    // End of variables declaration//GEN-END:variables
}