/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import SocketsImpl.Player;
import abstraction.AWeapon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.BOTTOM;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Utilities;

/**
 *
 * @author Marco Gamboa
 */
public class ActionWindow extends javax.swing.JFrame {
    private String idRival;
    private Player player;
    private HashMap<String, ExtendedDefaultCharacter> lstCharacters;
    private List<JButton> btnCharacters;

    /**
     * Creates new form ActionWindow
     */
    public ActionWindow() {

        initComponents();
        lstCharacters = new HashMap<>();
        btnCharacters = new ArrayList<>();
        txaScores.setText(" RANKING\n 1. diemora56 [135/90]\n 2. helosama666 [100/50]\n 3. \n 4.\n 5. \n 6.");
        txaOwnInfo.setText(" MYSTATUS\n Wins:135\n Loses:90 \n Attacks:1935 \n Succes:1000 \n Failed:935 \n Giveup:5");
        txaRivalInfo.setText(" AGAINST\n Wins:80\n Loses:15 \n Attacks:885 \n Succes:200 \n Failed:300 \n Giveup:2");
        txaCommands.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                processLine();
     
            }
        }});
    }

    
    private void processLine(){

   
    }
    public void setPlayer(Player pPlayer) {
        this.player = pPlayer;

        ImageIcon imageIcons = new ImageIcon("fight1.jpg");
        Image images = imageIcons.getImage();
        Image newimgs = images.getScaledInstance(lblRivalChar.getWidth(), lblRivalChar.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lblRivalChar.setIcon(new ImageIcon(newimgs));

        for (int i = 0; i < player.getWarriors().size(); i++) {
            JButton button = new JButton(player.getWarriors().get(i).getName());
            button.setName(player.getWarriors().get(i).getName());
            button.setBorder(new EtchedBorder());
            button.setPreferredSize(new Dimension(100, 200));
            button.setForeground(Color.GREEN);
            JLabel lbl = new JLabel(player.getWarriors().get(i).getName());
            lbl.setForeground(Color.white);
            button.add(lbl);
            lstCharacters.put(player.getWarriors().get(i).getName(), player.getWarriors().get(i));
            btnCharacters.add(button);
            button.setActionCommand(button.getName());
            ImageIcon imageIcon = new ImageIcon(player.getWarriors().get(i).
                    getAppearance(0).getLook(DefaultCharacterAppearance.codes.valueOf("DEFAULT")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(100, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            button.setIcon(new ImageIcon(newimg));
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectChar(e.getActionCommand());
                    lblTeamPaneCharName.setText(e.getActionCommand());
                }
            };
            button.addActionListener(actionListener);

            TeamPane.add(button);
            setWeapons(player.getWarriors().get(i).getWeapons());
        }

        TeamPane.revalidate();
        TeamPane.repaint();
    }

    public void setIdRival(String idRival) {
        this.idRival = idRival;
    }
    
    public void setWeapons(ArrayList<AWeapon> weapons) {
        String text = "";
        for (AWeapon weapon : weapons) {
            ExtendedDefaultWeapon eWeapon = (ExtendedDefaultWeapon) weapon;
            text += "\n " + eWeapon.getName();
            for (int pors : eWeapon.getElements().getPercentages()) {
                text += "  " + pors;
            }
        }
        txaWeapons.setText(text);

    }

    private void selectChar(String name) {
        TitledBorder border = new TitledBorder("SELECTED");
        border.setTitleColor(Color.WHITE);
        for (JButton button : btnCharacters) {
            button.setBorder(new EtchedBorder());
            if (button.getName().equals(name)) {
                button.setBorder(border);
                ImageIcon imageIcon = new ImageIcon(lstCharacters.get(name).getAppearance(0).
                        getLook(DefaultCharacterAppearance.codes.valueOf("ATTACK")));
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(lblOwnChar.getWidth(), lblOwnChar.getHeight(), java.awt.Image.SCALE_SMOOTH);
                lblOwnChar.setIcon(new ImageIcon(newimg));
                lblOwnChar.add(new JLabel(button.getName()));
                lblOwnChar.repaint();
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
        jLabel1 = new javax.swing.JLabel();
        lblTeamPaneCharName = new javax.swing.JLabel();
        lblTeamPaneHealth = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TeamPane = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txaWeapons = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lblRivalChar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblOwnChar = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaCommands = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaResults = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txaOwnInfo = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txaScores = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txaRivalInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 0));
        jLabel1.setText("YOUR TEAM");

        lblTeamPaneCharName.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneCharName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneCharName.setForeground(new java.awt.Color(0, 255, 0));
        lblTeamPaneCharName.setText("Dark Lord");

        lblTeamPaneHealth.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneHealth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneHealth.setForeground(new java.awt.Color(0, 255, 0));
        lblTeamPaneHealth.setText("100%");

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TeamPane.setBackground(new java.awt.Color(0, 0, 0));
        TeamPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TeamPane.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(TeamPane);

        txaWeapons.setEditable(false);
        txaWeapons.setBackground(new java.awt.Color(0, 0, 0));
        txaWeapons.setColumns(20);
        txaWeapons.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txaWeapons.setForeground(new java.awt.Color(0, 153, 51));
        txaWeapons.setRows(5);
        txaWeapons.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setViewportView(txaWeapons);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTeamPaneCharName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTeamPaneHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTeamPaneCharName)
                    .addComponent(lblTeamPaneHealth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRivalChar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRivalChar, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        lblOwnChar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOwnChar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOwnChar, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));

        txaCommands.setBackground(new java.awt.Color(0, 0, 0));
        txaCommands.setColumns(20);
        txaCommands.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txaCommands.setForeground(new java.awt.Color(0, 153, 0));
        txaCommands.setRows(5);
        jScrollPane2.setViewportView(txaCommands);

        txaResults.setBackground(new java.awt.Color(0, 0, 0));
        txaResults.setColumns(20);
        txaResults.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txaResults.setForeground(new java.awt.Color(0, 153, 0));
        txaResults.setRows(5);
        jScrollPane3.setViewportView(txaResults);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
        );

        txaOwnInfo.setEditable(false);
        txaOwnInfo.setBackground(new java.awt.Color(0, 0, 0));
        txaOwnInfo.setColumns(20);
        txaOwnInfo.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txaOwnInfo.setForeground(new java.awt.Color(51, 255, 0));
        txaOwnInfo.setRows(5);
        jScrollPane5.setViewportView(txaOwnInfo);

        txaScores.setEditable(false);
        txaScores.setBackground(new java.awt.Color(0, 0, 0));
        txaScores.setColumns(20);
        txaScores.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txaScores.setForeground(new java.awt.Color(51, 255, 0));
        txaScores.setRows(5);
        jScrollPane7.setViewportView(txaScores);

        txaRivalInfo.setEditable(false);
        txaRivalInfo.setBackground(new java.awt.Color(0, 0, 0));
        txaRivalInfo.setColumns(20);
        txaRivalInfo.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txaRivalInfo.setForeground(new java.awt.Color(51, 255, 0));
        txaRivalInfo.setRows(5);
        jScrollPane8.setViewportView(txaRivalInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActionWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TeamPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblOwnChar;
    private javax.swing.JLabel lblRivalChar;
    private javax.swing.JLabel lblTeamPaneCharName;
    private javax.swing.JLabel lblTeamPaneHealth;
    private javax.swing.JTextArea txaCommands;
    private javax.swing.JTextArea txaOwnInfo;
    private javax.swing.JTextArea txaResults;
    private javax.swing.JTextArea txaRivalInfo;
    private javax.swing.JTextArea txaScores;
    private javax.swing.JTextArea txaWeapons;
    // End of variables declaration//GEN-END:variables
}
