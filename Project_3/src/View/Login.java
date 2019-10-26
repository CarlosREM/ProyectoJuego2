/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ADT.CharacterPrototypeFactory;
import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import ResourcesImplementations.ExtendedPrototypeController;
import SocketsImpl.Player;
import abstraction.ACharacter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import utils.Elements;

/**
 *
 * @author Marco Gamboa
 */
public class Login extends javax.swing.JFrame {

    List<JButton> btnChars;
    HashMap<String, ExtendedDefaultCharacter> selectedChars;
    HashMap<String, ExtendedDefaultCharacter> chars;
    static Font customFont = new Font("Arial", Font.BOLD, 12);

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        btnChars = new ArrayList<>();
        selectedChars = new HashMap();
        chars = new HashMap();
        this.setResizable(false);
        setCharacters();
        

    }

    public void setCharacters() {
        ExtendedPrototypeController.loadDefaultPrototypes();
        List<ACharacter> tempList = CharacterPrototypeFactory.getAllCharacters();
        
        for (int i = 0; i < tempList.size(); i++) {
            
            ExtendedDefaultCharacter temp =(ExtendedDefaultCharacter) tempList.get(i);
            chars.put(temp.getName(), temp);
            JButton button = new JButton() {
                public JToolTip createToolTip() {
                    JToolTip tip = super.createToolTip();
                    tip.setOpaque(true);
                    tip.setForeground(Color.black);
                    tip.revalidate();
                    tip.repaint();
                    tip.setFont(new Font("Arial", Font.BOLD, 14));
                    return tip;
                }
            };
            button.setName(temp.getName());
            button.setToolTipText("[" + temp.getType().toString()+ "]");
            button.setBorder(new EtchedBorder());
            JLabel lbl = new JLabel(button.getName());
            lbl.setFont(customFont);
            lbl.setForeground(Color.white);
            button.add(lbl);
            button.setLayout(new GridLayout());
            button.setPreferredSize(new Dimension(140, 210));
            button.setActionCommand(temp.getName());
            button.setBackground(Color.black);
            
            Image image = null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream(temp.getAppearance(temp.getLevel()).getLook(DefaultCharacterAppearance.codes.valueOf("DEFAULT"))));
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                    if (selectedChars.size() < 4) {
                        button.setBorder(border);
                        selectedChars.put(name, chars.get(name));
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
        txfHost = new javax.swing.JTextField();
        btnPlay = new javax.swing.JButton();
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

        txfHost.setBackground(new java.awt.Color(0, 0, 0));
        txfHost.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txfHost.setForeground(new java.awt.Color(0, 153, 0));
        txfHost.setText("192.168.100.121");
        txfHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfHostActionPerformed(evt);
            }
        });

        btnPlay.setBackground(new java.awt.Color(0, 0, 0));
        btnPlay.setForeground(new java.awt.Color(0, 153, 0));
        btnPlay.setText("START");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
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
                                .addComponent(txfHost, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnPlay)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (selectedChars.size() < 4) {
            JOptionPane.showMessageDialog(null, "Not enough characters", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ActionWindow aw = new ActionWindow();
            try {
                aw.setPlayer(new Player(txfNickname.getText(), txfHost.getText(), new ArrayList<>(selectedChars.values()), aw));
                aw.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void txfNicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfNicknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfNicknameActionPerformed

    private void txfHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfHostActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txfHost;
    private javax.swing.JTextField txfNickname;
    // End of variables declaration//GEN-END:variables
}
