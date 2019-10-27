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
import SocketsImpl.Messages.DuelStateMessage;
import SocketsImpl.Messages.RequestMessage;
import View.ActionWindow;
import View.PlayersWindow;
import abstraction.ACharacter;
import abstraction.AWeapon;
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

    public void attack(String own, String weapon, String riv) {
        AttackMessage am = new AttackMessage();
        am.setAttacked(riv);
        ExtendedDefaultWeapon ew;
        for (ExtendedDefaultCharacter c : warriors) {
            if (c.getName().equals(own)) {
                am.setAttacker(c);
                for (int i = 0; i < c.getWeapons().size(); i++) {
                    if (c.getWeapons().get(i).getName().equals(weapon)) {
                        am.setWeapon(i);
                        ew = (ExtendedDefaultWeapon) c.getWeapons().get(i);
                        if (ew.isAvailable()) {
                            this.publisher.publish(am);
                            ew.setAvailable(false);
                        } else {
                            this.client.putResultText("Error: " + weapon + " is not available");
                        }

                    }
                }
            }
        }
    }

    public void takeAttack(AttackMessage am) {
        ExtendedDefaultCharacter ec = null;
        ExtendedDefaultWeapon ew;
        for (ExtendedDefaultCharacter c : this.getWarriors()) {
            if (c.getName().equals(am.getAttacked())) {
                ec = c;
            }
        }
        for (AWeapon w : am.getAttacker().getWeapons()) {
            ew = (ExtendedDefaultWeapon) w;
            if (w.getName().equals(am.getWeapon())) {
                w.use(ec);
            }
        }

        client.takeAttack("", am.getAttacker().getAppearance(1).
                getLook(DefaultCharacterAppearance.codes.valueOf("ATTACK")));

        this.publishState(true);

    }

    public void surrender() {

    }

    public void mutualSurrender() {

    }

    public void pass() {

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
        String strMessage = "";
        strMessage += "AGAINST" + "\n" + dsm.getTopic() + "\n\n";
        for (DuelStateMessage.WarriorCoreInfo e : dsm.getWarriors()) {
            strMessage += e.getName() + "\n[" + e.getType().toString() + "]\n";
            strMessage += "HP: " + e.getCurrentHealthPoints() + "%\n\n";
        }
        this.client.putRivalData(strMessage);
    }
}
