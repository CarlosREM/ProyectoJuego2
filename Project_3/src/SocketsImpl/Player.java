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

    public String matchStart;

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

    public void attackExtraChar(String own1, String weapon1, String own2, String weapon2) {
        DuelStateMessage dm = new DuelStateMessage(own1);
        AttackMessage am1 = new AttackMessage();
        AttackMessage am2 = new AttackMessage();
        dm.setWarriors(warriors);

        am1.setTopic(this.publisher.getTopic());
        am2.setTopic(this.publisher.getTopic());

        am1.setWeapon(weapon1);
        am2.setWeapon(weapon2);

        DuelStateMessage.WarriorCoreInfo warrior1 = dm.getWarriors().stream().filter(warr -> warr.getName().equals(own1)).findAny().orElse(null);
        DuelStateMessage.WarriorCoreInfo warrior2 = dm.getWarriors().stream().filter(warr -> warr.getName().equals(own2)).findAny().orElse(null);

        am1.setAttacker(warrior1);
        am2.setAttacker(warrior2);

        ExtendedDefaultWeapon ew1 = (ExtendedDefaultWeapon) warrior1.getWeapons().stream().filter(warr -> warr.getName().equals(weapon1)).findAny().orElse(null);
        ExtendedDefaultWeapon ew2 = (ExtendedDefaultWeapon) warrior2.getWeapons().stream().filter(warr -> warr.getName().equals(weapon2)).findAny().orElse(null);

        if (ew1.isAvailable() && ew2.isAvailable()) {
            ew1.setAvailable(false);
            ew2.setAvailable(false);

            AttackPlusMessage apm = new AttackPlusMessage();
            apm.setAttackMsg1(am1);
            apm.setAttackMsg2(am2);
            publish(apm);

            client.lblAttackPlus.setVisible(false);
            client.setEnableCmd(false);
            client.putResultText(own1 + " and " + own2 + " are attacking...");
        } else {
            client.putResultText("ERROR: Weapons already used");
        }

    }

    public void attackExtraWeapon(String own, String weapon1, String weapon2) {
        DuelStateMessage dm = new DuelStateMessage(own);
        AttackMessage am1 = new AttackMessage();
        AttackMessage am2 = new AttackMessage();
        dm.setWarriors(warriors);

        am1.setTopic(this.publisher.getTopic());
        am2.setTopic(this.publisher.getTopic());

        am1.setWeapon(weapon1);
        am2.setWeapon(weapon2);

        DuelStateMessage.WarriorCoreInfo warrior = dm.getWarriors().stream().filter(warr -> warr.getName().equals(own)).findAny().orElse(null);
        am1.setAttacker(warrior);
        am2.setAttacker(warrior);

        ExtendedDefaultWeapon ew1 = (ExtendedDefaultWeapon) warrior.getWeapons().stream().filter(warr -> warr.getName().equals(weapon1)).findAny().orElse(null);
        ExtendedDefaultWeapon ew2 = (ExtendedDefaultWeapon) warrior.getWeapons().stream().filter(warr -> warr.getName().equals(weapon2)).findAny().orElse(null);

        if (ew1.isAvailable() && ew2.isAvailable() && (!weapon1.equals(weapon2))) {
            ew1.setAvailable(false);
            ew2.setAvailable(false);

            AttackPlusMessage apm = new AttackPlusMessage();
            apm.setAttackMsg1(am1);
            apm.setAttackMsg2(am2);
            publish(apm);

            client.lblAttackPlus.setVisible(false);
            client.setEnableCmd(false);
            client.putResultText(am1.getAttacker().getName() + " is attacking...");
        } else {
            if (weapon1.equals(weapon2)) {
                client.putResultText("ERROR: Same weapon");
            } else {
                client.putResultText("ERROR: Weapons already used");
            }
        }
    }

    public void attack(String own, String weapon) {
        AttackMessage am = new AttackMessage();
        DuelStateMessage dm = new DuelStateMessage(own);
        dm.setWarriors(warriors);

        am.setWeapon(weapon);
        am.setTopic(this.publisher.getTopic());

        DuelStateMessage.WarriorCoreInfo warrior = dm.getWarriors().stream().filter(wea -> wea.getName().equals(own)).findAny().orElse(null);
        am.setAttacker(warrior);

        ExtendedDefaultWeapon ew = (ExtendedDefaultWeapon) warrior.getWeapons().stream().filter(warr -> warr.getName().equals(weapon)).findAny().orElse(null);
        if (ew.isAvailable()) {
            ew.setAvailable(false);
            client.setEnableCmd(false);
            client.putResultText(am.getAttacker().getName() + " is attacking...");
            publisher.publish(am);
        } else {
            this.client.putResultText(weapon + " already used");
        }

    }

    public void publish(AMessage message) {
        this.publisher.publish(message);
    }

    public void takeAttack(AttackMessage am) {

        int accumDamage = 0;
        String info = "Attacked by\n" + am.getAttacker().getName() + " ";
        info += "[" + am.getAttacker().getType().toString() + "]\n";
        info += "\nweapon: " + am.getWeapon();

        ExtendedDefaultWeapon ew = (ExtendedDefaultWeapon) am.getAttacker().getWeapons().stream().filter(wea -> wea.getName().equals(am.getWeapon())).findAny().orElse(null);

        for (ExtendedDefaultCharacter c : this.getWarriors()) {
            ew.use(c);
            accumDamage += ew.getActualAttack();
        }

        info += "\ndamage: " + accumDamage + "%";
        client.takeAttack(info, am.getAttacker().getAppearances().
                get(1).getLook(DefaultCharacterAppearance.codes.ATTACK));

        RequestMessage succesMessage = new RequestMessage();
        succesMessage.setRequestId(53);
        succesMessage.setTopic(am.getTopic());
        if (accumDamage > 100) {
            succesMessage.setRequestString("succes");
        } else {
            succesMessage.setRequestString("fail");
        }

        this.publisher.publish(succesMessage);

        RequestMessage rm = new RequestMessage();
        String info2 = "You attacked with\n" + am.getAttacker().getName()
                + " [" + am.getAttacker().getType().toString() + "]\n\nweapon:"
                + am.getWeapon() + "\ndamage: " + accumDamage + "%\n";
        info2 += ";" + am.getAttacker().getAppearances().
                get(1).getLook(DefaultCharacterAppearance.codes.ATTACK);

        rm.setRequestId(52);
        rm.setRequestString(info2);
        this.publisher.publish(rm);

        client.putResultText("You were attacked!!");
        client.setEnableCmd(true);
        this.publishState(false);

        if (imDead()) {
            surrender("You're defeated");
        }
    }

    private boolean imDead() {
        for (ExtendedDefaultCharacter edc : getWarriors()) {
            if (edc.getCurrentHealthPoints() > 0) {
                return false;
            }
        }
        return true;
    }

    public String getTopic() {
        return this.publisher.getTopic();
    }

    public void surrender(String text) {
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(51);
        rm.setTopic(this.getTopic());
        rm.setRequestString("Winner!");
        this.publisher.publish(rm);
        endGame(text);
        publishState(false);
    }

    public void endGame(String text) {
        this.client.putRivalData("AGAINST");
        this.client.putResultText(text);
        client.setEnableCmd(true);
        restoreDefaults();
        client.setEnableSearchPlayers(true);
    }

    public void mutualSurrender() {
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(100);
        rm.setRequestString(publisher.getTopic());
        publish(rm);
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
        m.setTopic(this.getTopic());
        this.publisher.publish(m);
    }

    public void setRivalState(DuelStateMessage dsm) {
        
        String strMessage = "";
        strMessage += "AGAINST" + " [" + dsm.getTopic() + "]\n"
                + dsm.getRivalInfo() + "\n\n";
        for (DuelStateMessage.WarriorCoreInfo e : dsm.getWarriors()) {
            strMessage += e.getName() + "\n[" + e.getType().toString() + "]\n";
            strMessage += "HP: " + e.getCurrentHealthPoints() + "%\n\n";
        }
        this.client.putRivalData(strMessage);
    }

    public void addToMatchLog(String info) {
        RequestMessage rm = new RequestMessage();
        rm.setRequestId(77);
        rm.setRequestString(info);
        rm.setTopic(this.matchStart);
        publisher.publish(rm);
    }

    public void disconnect() {
        this.publisher.disconnect();
        this.subscriber.disconnect();

    }
}
