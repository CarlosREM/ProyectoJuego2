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
    private int weapon;
    private String attacked;
    private ExtendedDefaultCharacter attacker;

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public String getAttacked() {
        return attacked;
    }

    public void setAttacked(String attacked) {
        this.attacked = attacked;
    }

    public ExtendedDefaultCharacter getAttacker() {
        return attacker;
    }

    public void setAttacker(ExtendedDefaultCharacter attacker) {
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
