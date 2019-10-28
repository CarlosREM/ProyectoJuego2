/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import commsapi.Message.AMessage;

/**
 *
 * @author Diego Murillo
 */
public class AttackPlusMessage extends AMessage{
    private AttackMessage attackMsg1;
    private AttackMessage attackMsg2;
    public AttackPlusMessage(){
        super("Plus");
    }    

    public AttackMessage getAttackMsg1() {
        return attackMsg1;
    }

    public void setAttackMsg1(AttackMessage attackMsg1) {
        this.attackMsg1 = attackMsg1;
    }

    public AttackMessage getAttackMsg2() {
        return attackMsg2;
    }

    public void setAttackMsg2(AttackMessage attackMsg2) {
        this.attackMsg2 = attackMsg2;
    }
    
}
