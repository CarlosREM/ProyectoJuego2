/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import ADT.ExtendedDefaultCharacter;
import commsapi.Message.AMessage;

/**
 *
 * @author Diego Murillo
 */
public class AttackMessage extends AMessage{
    private int RequestId;
    private String RequestString;
    private String weapon;
    private String attacked;
    private DuelStateMessage.WarriorCoreInfo attacker;

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getAttacked() {
        return attacked;
    }

    public void setAttacked(String attacked) {
        this.attacked = attacked;
    }

    public DuelStateMessage.WarriorCoreInfo getAttacker() {
        return attacker;
    }

    public void setAttacker(DuelStateMessage.WarriorCoreInfo attacker) {
        this.attacker = attacker;
    }
    
    public AttackMessage(){
        super("Attact");
    }

    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int RequestId) {
        this.RequestId = RequestId;
    }

    public String getRequestString() {
        return RequestString;
    }

    public void setRequestString(String RequestString) {
        this.RequestString = RequestString;
    }
    
    
}
