/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import ADT.DefaultCharacterAppearance;
import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import SocketsImpl.Messages.AttackMessage;
import SocketsImpl.Messages.AttackPlusMessage;
import SocketsImpl.Messages.DuelStateMessage;
import SocketsImpl.Messages.RequestMessage;
import View.ActionWindow;
import View.PlayersWindow;
import abstraction.ACharacter;
import abstraction.AWeapon;
import commsapi.Message.AMessage;
import commsapi.Utils.PropertiesUtil;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diego Murillo
 */
public class Player {

    private PlayerPublisher publisher;
    private PlayerSubscriber subscriber;

    private List<ExtendedDefaultCharacter> warriors;
    private ActionWindow client;

    public Player(String id, List<ExtendedDefaultCharacter> warriors, ActionWindow client) throws IOException {
        publisher = new PlayerPublisher(id, this);
        subscriber = new PlayerSubscriber(id, this);
        this.warriors = warriors;
        this.client = client;
    }

    public Player(String id, String host, List<ExtendedDefaultCharacter> warriors, ActionWindow client) throws IOException {
        int defaultPubPort = Integer.parseInt(PropertiesUtil.getInstance().getProperty("defaultPubPort"));
        int defaultSubPort = Integer.parseInt(PropertiesUtil.getInstance().getProperty("defaultSubPort"));
        publisher = new PlayerPublisher(id, host, defaultPubPort, this);
        subscriber = new PlayerSubscriber(id, host, defaultSubPort, this);
        this.warriors = warriors;
        this.client = client;
    }

    public void restoreDefaults() {
        for (ExtendedDefaultCharacter c : warriors) {
            c.setCurrentHealthPoints(100);
            for (AWeapon w : c.getWeapons()) {
                ExtendedDefaultWeapon ew = (ExtendedDefaultWeapon) w;
                ew.setAvailable(true);
            }
        }
        this.client.setPlayer(this);
        client.setEnableCmd(true);
    }

    public List<ExtendedDefaultCharacter> getWarriors() {
        return warriors;
    }

    public void setWarriors(List<ExtendedDefaultCharacter> warriors) {
        this.warriors = warriors;
    }

    public void askForTopics() {
        subscriber.askForTopics();
    }

    public void sendChat(String text) {
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(50);
        rm.setRequestString(text);
        this.publisher.publish(rm);
    }

    public void reciveChat(String text) {
        this.client.putResultText(text);
    }

    public void showTopics(List<String> topics) {
        PlayersWindow pw = new PlayersWindow();
        pw.setActionWindos(client);
        pw.fillPlayers(topics);
        pw.setVisible(true);
    }

    public void attackExtraChar(String own1, String weapon1, String own2, String weapon2, String riv) {
        DuelStateMessage dm = new DuelStateMessage(riv);
        AttackMessage am1 = new AttackMessage();
        AttackMessage am2 = new AttackMessage();
        dm.setWarriors(warriors);

        am1.setAttacked(riv);
        am1.setWeapon(weapon1);

        am2.setAttacked(riv);
        am2.setWeapon(weapon2);

        ExtendedDefaultWeapon ew1 = null;
        ExtendedDefaultWeapon ew2 = null;
        for (DuelStateMessage.WarriorCoreInfo warrior : dm.getWarriors()) {
            if (warrior.getName().equals(own1)) {
                am1.setAttacker(warrior);
                for (int i = 0; i < warrior.getWeapons().size(); i++) {
                    if (warrior.getWeapons().get(i).getName().equals(weapon1)) {
                        ew1 = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                    }
                }
            }
            if (warrior.getName().equals(own2)) {
                am2.setAttacker(warrior);
                for (int i = 0; i < warrior.getWeapons().size(); i++) {
                    if (warrior.getWeapons().get(i).getName().equals(weapon2)) {
                        ew2 = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                    }
                }
            }
        }
        if (ew1.isAvailable() && ew2.isAvailable()) {
            ew1.setAvailable(false);
            ew2.setAvailable(false);

            AttackPlusMessage apm = new AttackPlusMessage();
            apm.setAttackMsg1(am1);
            apm.setAttackMsg2(am2);
            publish(apm);
            client.lblAttackPlus.setVisible(false);
            client.setEnableCmd(false);
            client.putResultText(own1 + " and "+ own2 + " are attacking...");
        } else {
            client.putResultText("ERROR: Weapons already used");
        }

    }

    public void attackExtraWeapon(String own, String weapon1, String weapon2, String riv) {
        DuelStateMessage dm = new DuelStateMessage(riv);
        AttackMessage am1 = new AttackMessage();
        AttackMessage am2 = new AttackMessage();
        dm.setWarriors(warriors);

        am1.setAttacked(riv);
        am1.setWeapon(weapon1);

        am2.setAttacked(riv);
        am2.setWeapon(weapon2);

        ExtendedDefaultWeapon ew1 = null;
        ExtendedDefaultWeapon ew2 = null;
        for (DuelStateMessage.WarriorCoreInfo warrior : dm.getWarriors()) {
            if (warrior.getName().equals(own)) {
                am1.setAttacker(warrior);
                am2.setAttacker(warrior);
                for (int i = 0; i < warrior.getWeapons().size(); i++) {
                    if (warrior.getWeapons().get(i).getName().equals(weapon1)) {
                        ew1 = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                    }
                    if (warrior.getWeapons().get(i).getName().equals(weapon2)) {
                        ew2 = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                    }
                }
            }
        }
        if (ew1.isAvailable() && ew2.isAvailable() && (!weapon1.equals(weapon2))) {
            ew1.setAvailable(false);
            ew2.setAvailable(false);
            
            AttackPlusMessage apm = new AttackPlusMessage();
            apm.setAttackMsg1(am1);
            apm.setAttackMsg2(am2);
            publish(apm);
            
            client.lblAttackPlus.setVisible(false);
            client.setEnableCmd(false);
            client.putResultText(am1.getAttacker().getName()+" is attacking...");
        } else {
            client.putResultText("ERROR: Weapons already used");
        }
    }

    public void attack(String own, String weapon, String riv) {
        AttackMessage am = new AttackMessage();
        DuelStateMessage dm = new DuelStateMessage(own);
        dm.setWarriors(warriors);

        am.setAttacked(riv);
        am.setWeapon(weapon);

        ExtendedDefaultWeapon ew;

        for (DuelStateMessage.WarriorCoreInfo warrior : dm.getWarriors()) {
            if (warrior.getName().equals(own)) {
                am.setAttacker(warrior);
                for (int i = 0; i < warrior.getWeapons().size(); i++) {
                    if (warrior.getWeapons().get(i).getName().equals(weapon)) {
                        ew = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                        if (ew.isAvailable()) {
                            ew.setAvailable(false);
                            client.setEnableCmd(false);
                            client.putResultText(am.getAttacker().getName() + " is attacking...");
                            publisher.publish(am);
                        } else {
                            this.client.putResultText(weapon + " already used");
                        }
                    }
                }
            }
        }
    }

    public void publish(AMessage message) {
        this.publisher.publish(message);
    }

    public void takeAttack(AttackMessage am) {
        ExtendedDefaultCharacter ec = null;
        ExtendedDefaultWeapon ew;
        String info = "Attacked by\n" + am.getAttacker().getName() + " ";
        info += "[" + am.getAttacker().getType().toString() + "]\n";
        int actualAttack = 0;
        for (ExtendedDefaultCharacter c : this.getWarriors()) {
            if (c.getName().equals(am.getAttacked())) {
                ec = c;
            }
        }
        for (AWeapon w : am.getAttacker().getWeapons()) {
            ew = (ExtendedDefaultWeapon) w;
            if (ew.getName().equals(am.getWeapon())) {
                ew.use(ec);
                info += "weapon: " + am.getWeapon() + "\n";
                info += "damage: " + ew.getActualAttack() + "%\n";
                actualAttack = ew.getActualAttack();
            }
        }
        info += "\nAffected\n" + am.getAttacked();
        client.takeAttack(info, am.getAttacker().getAppearances().
                get(1).getLook(DefaultCharacterAppearance.codes.ATTACK));

        this.publishState(true);

        RequestMessage rm = new RequestMessage();
        String info2 = "You attacked with\n" + am.getAttacker().getName()
                + " [" + am.getAttacker().getType().toString() + "]\nweapon:"
                + am.getWeapon() + "\ndamage: " + actualAttack + "%";
        info2 += "\nAffected\n" + am.getAttacked();
        rm.setRequestId(52);
        rm.setRequestString(info2);
        rm.setTopic(am.getAttacker().getAppearances().
                get(1).getLook(DefaultCharacterAppearance.codes.ATTACK));
        this.publisher.publish(rm);
        client.putResultText(am.getAttacked() + " was attacked...");
        client.setEnableCmd(true);
    }

    public String getTopic() {
        return this.publisher.getTopic();
    }

    public void surrender() {
        this.client.setEnableSearchPlayers(true);
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(51);
        rm.setRequestString("Winner!");
        this.client.putRivalData("AGAINST");
        this.publisher.publish(rm);
        for (int i = 0; i < this.subscriber.getSubscriptions().size(); i++) {
            this.subscriber.unsubscribe(this.subscriber.getSubscriptions().get(i));
        }
        client.setEnableCmd(false);
    }

    public void win(String text) {
        for (int i = 0; i < this.subscriber.getSubscriptions().size(); i++) {
            this.subscriber.unsubscribe(this.subscriber.getSubscriptions().get(i));
        }
        this.client.putRivalData("AGAINST");
        this.client.setEnableSearchPlayers(true);
        this.client.putResultText(text);
        client.setEnableCmd(false);

    }

    public void mutualSurrender() {

    }

    public void pass() {
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(60);
        rm.setRequestString(publisher.getTopic());
        publisher.publish(rm);
        client.setEnableCmd(false);
        client.putResultText("You pass!");
    }

    public void askForDuel(String opponent) {
        this.subscriber.subscribe(opponent);
    }

    public ActionWindow getClient() {
        return client;
    }

    public void setClient(ActionWindow client) {
        this.client = client;
    }

    public void publishState(boolean initialState) {
        DuelStateMessage m = new DuelStateMessage(this.publisher.getTopic());
        m.setStart(initialState);
        m.setWarriors(this.getWarriors());
        this.publisher.publish(m);
    }

    public void setRivalState(DuelStateMessage dsm) {
        this.client.setEnableSearchPlayers(false);
        String strMessage = "";
        strMessage += "AGAINST" + " [" + dsm.getTopic() + "]\n"+
                    dsm.getRivalInfo()+"\n\n";
        for (DuelStateMessage.WarriorCoreInfo e : dsm.getWarriors()) {
            strMessage += e.getName() + "\n[" + e.getType().toString() + "]\n";
            strMessage += "HP: " + e.getCurrentHealthPoints() + "%\n\n";
        }
        this.client.putRivalData(strMessage);
    }
    
    public void disconnect(){
        this.publisher.disconnect();
        this.subscriber.disconnect();
        
    }
}
