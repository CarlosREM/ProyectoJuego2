/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import SocketsImpl.Messages.ConMessage;
import SocketsImpl.Messages.RequestMessage;
import commsapi.Message.AMessage;
import commsapi.Subscriber.ASubscriber;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Murillo
 */
public class PlayerSubscriber extends ASubscriber{
    Player player;
    
    public PlayerSubscriber(String id, Player player) throws IOException{
        super();
        this.player = player;
        this.setId(id);
    }
    
    public PlayerSubscriber(String id, String host, int port, Player player) throws IOException{
        super(host, port);
        this.player = player;
        this.setId(id);
    }
    
    @Override
    public void subscribe(String topic) {
        RequestMessage m = new RequestMessage();
        m.setRequestId(1);
        m.setRequestString(topic);
        
        
        sendMessage(m);
        
        this.addSubscription(topic);
    }

    @Override
    public void unsubscribe(String topic) {
        this.subscriptions.remove(topic);
        RequestMessage m = new RequestMessage();
        m.setRequestId(2);
        m.setRequestString(topic);
   
        sendMessage(m);     
    }

    @Override
    public void askForTopics() {
        RequestMessage m = new RequestMessage();
        m.setRequestId(0);
        
        sendMessage(m);
    }

    @Override
    public void sendMessage(AMessage message) {
        try {
            this.intermediate.sendMessage(message);
        } catch (IOException ex) {
            Logger.getLogger(PlayerSubscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receivedMessage(AMessage message) {
        System.out.println("message recieved on subscriber " + message.serialize());
        
        if(message instanceof ConMessage){
            ConMessage m = (ConMessage) message;
            this.setConnected(m.isAcceptedConnection());
            System.out.println(m.getConnMessage());
        }
    }
    
}
