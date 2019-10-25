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
public class InitialDuelStateMessage extends AMessage{
    public InitialDuelStateMessage(){
        super("Duel started");
    }
}
