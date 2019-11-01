/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import ADT.Invoker;
import SocketsImpl.Player;
import abstraction.AWeapon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Marco Gamboa
 */
public class ActionWindow extends javax.swing.JFrame {

    private Player player;
    private HashMap<String, ExtendedDefaultCharacter> lstCharacters;
    private List<JButton> btnCharacters;
    public String selectedChar;

    private static Font tipFont = new Font("Arial", Font.BOLD, 14);

    /**
     * Creates new form ActionWindow
     */
    public ActionWindow() {

        initComponents();
        lstCharacters = new HashMap<>();
        btnCharacters = new ArrayList<>();
        this.lblAttackPlus.setVisible(false);
        txaCommands.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txaResults.append(">>" + txaCommands.getText() + "\n");
                    Invoker i = new Invoker();
                    i.execute(txaCommands.getText(), player);
                    txaCommands.setText("");
                }
            }
        });
    }

    public void restoreDefaults() {
        this.txaResults.setText("");
        this.lblAttackPlus.setVisible(false);
        
    }

    public void fillRanking(String text) {
        this.txaScores.setText(" RANKING\n" + text);
    }

    public void setEnableCmd(boolean option) {
        this.txaCommands.setEditable(option);
    }

    public void setPlayer(Player pPlayer) {
        this.player = pPlayer;
        this.lblOwnChar.setIcon(null);
        this.lblRivalChar.setIcon(null);
        this.txaOwnAttackInfo.setText("");
        this.txaRivalAttackInfo.setText("");
        this.txaResults.setText("");
        this.txaRivalInfo.setText("AGAINST");
        this.TeamPane.removeAll();
        for (int i = 0; i < player.getWarriors().size(); i++) {
            JButton button = new JButton(player.getWarriors().get(i).getName()) {
                public JToolTip createToolTip() {
                    JToolTip tip = super.createToolTip();
                    tip.setOpaque(true);
                    tip.setForeground(Color.black);
                    tip.revalidate();
                    tip.repaint();
                    tip.setFont(tipFont);
                    return tip;
                }
            };
            button.setName(player.getWarriors().get(i).getName());
            button.setToolTipText("[" + player.getWarriors().get(i).getType().toString() + "}");
            button.setBorder(new EtchedBorder());
            button.setPreferredSize(new Dimension(120, 200));
            button.setForeground(Color.GREEN);
            JLabel lbl = new JLabel(player.getWarriors().get(i).getName());
            lbl.setForeground(Color.white);
            button.setBackground(Color.black);
            button.add(lbl);
            lstCharacters.put(player.getWarriors().get(i).getName(), player.getWarriors().get(i));
            btnCharacters.add(button);
            button.setActionCommand(button.getName());

            ImageIcon imageIcon = new ImageIcon("src" + player.getWarriors().get(i).
                    getAppearance(player.getWarriors().get(i).getLevel()).
                    getLook(DefaultCharacterAppearance.codes.valueOf("DEFAULT")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(120, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            button.setIcon(new ImageIcon(newimg));
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectChar(e.getActionCommand());
                }
            };
            button.addActionListener(actionListener);

            TeamPane.add(button);

        }
        selectChar(btnCharacters.get(0).getActionCommand());

        TeamPane.revalidate();
        TeamPane.repaint();
    }

    public void selectChar(String name) {
        selectedChar = name;
        TitledBorder border = new TitledBorder("SELECTED");
        border.setTitleColor(Color.WHITE);
        for (JButton button : btnCharacters) {
            button.setBorder(new EtchedBorder());
            if (button.getName().equals(name)) {
                button.setBorder(border);
                setWeapons(lstCharacters.get(name).getWeapons());
                lblTeamPaneHealth.setText(lstCharacters.get(name).getCurrentHealthPoints() + "%");
                lblType.setText("[" + lstCharacters.get(name).getType().toString() + "]");
                lblTeamPaneCharName.setText(name);
            }
        }
    }

    public void attack(String info) {
        String[] arrInfo = info.split(";");
        ImageIcon imageIcon = new ImageIcon("src" + arrInfo[1]);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(lblOwnChar.getWidth(), lblOwnChar.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lblOwnChar.setIcon(new ImageIcon(newimg));
        lblOwnChar.repaint();
        txaOwnAttackInfo.setText(arrInfo[0]);
        fillMyStatus(arrInfo[2]);
    }

    public void setWeapons(ArrayList<AWeapon> weapons) {
        String text = "Weapon   FI AI WA WM BM EL IC AC SP IR\n";
        text +=       "*******************************************************";
        for (AWeapon weapon : weapons) {
            ExtendedDefaultWeapon eWeapon = (ExtendedDefaultWeapon) weapon;
            text += "\n" + eWeapon.getName()+"  ";
            for (int pors : eWeapon.getElements().getPercentages()) {
                text += " " + pors;
            }
            if (eWeapon.isAvailable()) {
                text += " Available";
            } else {
                text += " Not available";
            }
        }
        txaWeapons.setText(text);

    }

    public void takeAttack(String info, String filePath) {
        ImageIcon imageIcon = new ImageIcon("src" + filePath);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(lblRivalChar.getWidth(), lblRivalChar.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lblRivalChar.setIcon(new ImageIcon(newimg));
        lblRivalChar.repaint();
        txaRivalAttackInfo.setText(info);

    }

    public void fillMyStatus(String scores) {
        String strStatus = ("MY STATUS [" + player.getTopic() + "]\n");
        strStatus += scores + "\n";
        txaOwnInfo.setText(strStatus);
    }

    public void putResultText(String text) {
        this.txaResults.append(text + "\n");
    }

    public void putRivalData(String text) {
        this.txaRivalInfo.setText(text);
    }

    public void setEnableSearchPlayers(boolean option) {
        this.btnSelect.setEnabled(option);
    }

    public Player getPlayer() {
        return this.player;
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
        lblType = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblOwnChar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaOwnAttackInfo = new javax.swing.JTextArea();
        lblTeamPaneCharName2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaResults = new javax.swing.JTextArea();
        txaCommands = new javax.swing.JTextField();
        lblAttackPlus = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txaOwnInfo = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txaScores = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txaRivalInfo = new javax.swing.JTextArea();
        rivalPane = new javax.swing.JPanel();
        lblRivalChar = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txaRivalAttackInfo = new javax.swing.JTextArea();
        lblTeamPaneCharName1 = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 51));
        jLabel1.setText("YOUR TEAM");

        lblTeamPaneCharName.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneCharName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneCharName.setForeground(new java.awt.Color(0, 204, 51));
        lblTeamPaneCharName.setText("Dark Lord");

        lblTeamPaneHealth.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneHealth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneHealth.setForeground(new java.awt.Color(0, 204, 51));
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

        lblType.setBackground(new java.awt.Color(255, 255, 255));
        lblType.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblType.setForeground(new java.awt.Color(0, 204, 51));
        lblType.setText("[Black_Magic]");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblTeamPaneCharName)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblType)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTeamPaneHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(lblTeamPaneHealth)
                    .addComponent(lblType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        lblOwnChar.setText("jLabel2");

        txaOwnAttackInfo.setEditable(false);
        txaOwnAttackInfo.setBackground(new java.awt.Color(0, 0, 0));
        txaOwnAttackInfo.setColumns(20);
        txaOwnAttackInfo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txaOwnAttackInfo.setForeground(new java.awt.Color(0, 153, 51));
        txaOwnAttackInfo.setRows(5);
        jScrollPane2.setViewportView(txaOwnAttackInfo);

        lblTeamPaneCharName2.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneCharName2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneCharName2.setForeground(new java.awt.Color(0, 204, 51));
        lblTeamPaneCharName2.setText("Own activity");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOwnChar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTeamPaneCharName2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblOwnChar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTeamPaneCharName2)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));

        txaResults.setEditable(false);
        txaResults.setBackground(new java.awt.Color(0, 0, 0));
        txaResults.setColumns(20);
        txaResults.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txaResults.setForeground(new java.awt.Color(0, 153, 0));
        txaResults.setRows(5);
        jScrollPane3.setViewportView(txaResults);

        txaCommands.setBackground(new java.awt.Color(0, 0, 0));
        txaCommands.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txaCommands.setForeground(new java.awt.Color(0, 153, 51));

        lblAttackPlus.setBackground(new java.awt.Color(0, 0, 0));
        lblAttackPlus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAttackPlus.setForeground(new java.awt.Color(0, 204, 51));
        lblAttackPlus.setText("AttackPlus Is Available!!");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txaCommands, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAttackPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txaCommands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAttackPlus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        rivalPane.setBackground(new java.awt.Color(0, 0, 0));
        rivalPane.setForeground(new java.awt.Color(0, 0, 0));

        lblRivalChar.setText("jLabel2");

        txaRivalAttackInfo.setEditable(false);
        txaRivalAttackInfo.setBackground(new java.awt.Color(0, 0, 0));
        txaRivalAttackInfo.setColumns(20);
        txaRivalAttackInfo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txaRivalAttackInfo.setForeground(new java.awt.Color(0, 153, 51));
        txaRivalAttackInfo.setRows(5);
        jScrollPane9.setViewportView(txaRivalAttackInfo);

        lblTeamPaneCharName1.setBackground(new java.awt.Color(255, 255, 255));
        lblTeamPaneCharName1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTeamPaneCharName1.setForeground(new java.awt.Color(0, 204, 51));
        lblTeamPaneCharName1.setText("Rival activity");

        javax.swing.GroupLayout rivalPaneLayout = new javax.swing.GroupLayout(rivalPane);
        rivalPane.setLayout(rivalPaneLayout);
        rivalPaneLayout.setHorizontalGroup(
            rivalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rivalPaneLayout.createSequentialGroup()
                .addGroup(rivalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRivalChar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rivalPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTeamPaneCharName1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rivalPaneLayout.setVerticalGroup(
            rivalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rivalPaneLayout.createSequentialGroup()
                .addComponent(lblRivalChar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTeamPaneCharName1))
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnSelect.setBackground(new java.awt.Color(0, 0, 0));
        btnSelect.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSelect.setForeground(new java.awt.Color(0, 204, 51));
        btnSelect.setText("SELECT OPPONENT");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rivalPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rivalPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        player.askForTopics();
        player.restoreDefaults();
    }//GEN-LAST:event_btnSelectActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        player.disconnect();
    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JButton btnSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JLabel lblAttackPlus;
    private javax.swing.JLabel lblOwnChar;
    private javax.swing.JLabel lblRivalChar;
    private javax.swing.JLabel lblTeamPaneCharName;
    private javax.swing.JLabel lblTeamPaneCharName1;
    private javax.swing.JLabel lblTeamPaneCharName2;
    private javax.swing.JLabel lblTeamPaneHealth;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel rivalPane;
    private javax.swing.JTextField txaCommands;
    private javax.swing.JTextArea txaOwnAttackInfo;
    private javax.swing.JTextArea txaOwnInfo;
    private javax.swing.JTextArea txaResults;
    private javax.swing.JTextArea txaRivalAttackInfo;
    private javax.swing.JTextArea txaRivalInfo;
    private javax.swing.JTextArea txaScores;
    private javax.swing.JTextArea txaWeapons;
    // End of variables declaration//GEN-END:variables
}
