/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import SocketsImpl.Messages.ConMessage;
import SocketsImpl.Messages.DuelStateMessage;
import SocketsImpl.Messages.RequestMessage;
import SocketsImpl.Messages.TopicsMessage;
import commsapi.ContentServer.AContentServer;
import commsapi.ContentServer.PublisherHandler;
import commsapi.ContentServer.SubscriberHandler;
import commsapi.Message.AMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Murillo
 */
public class GameServer extends AContentServer{
    
    public GameServer() throws IOException{
        super();
        System.out.println("SERVER running");
        System.out.println(InetAddress.getLocalHost());
    }

    @Override
    public void processSubMessage(AMessage message, SubscriberHandler handler) {
        if(message instanceof RequestMessage){
            try {
                RequestMessage m = (RequestMessage) message;
                switch(m.getRequestId()){
                    case 0: //Asked for available players
                        broadcastTopics(handler);
                        break;
                        
                    case 1: //Initiate Duel
                        String topic = m.getRequestString();
                        SubscriberHandler oponent = this.subscribers.stream().filter(sub -> sub.getId().equals(topic)).findAny().orElse(null);
                        
                        this.registerSubscription(topic, handler);
                        this.registerSubscription(handler.getId(), oponent);
                        
                        RequestMessage m2 = new RequestMessage();
                        m2.setRequestId(777);
                        m2.setRequestString("Duel Start! " + handler.getId() + " vs " + topic);
                        oponent.sendMessage(m2);
                        handler.sendMessage(m2);
                       
                        break;
                        
                    case 99:   //set subscriberHandler id
                        handler.setId(m.getRequestString());
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void processPubMessage(AMessage message, PublisherHandler handler) {
        if(message instanceof DuelStateMessage){
            try {
                this.broadcastMessageSub(message, handler.getTopic());
            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void broadcastTopics(SubscriberHandler handler) {
        TopicsMessage m = new TopicsMessage();
        for(String key : this.subscriptions.keySet()){
            if(this.subscriptions.get(key).isEmpty() && !key.equals(handler.getId()))
                m.getTopics().add(key);
        }
        
        try {
            handler.sendMessage(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void acceptPubConnection(PublisherHandler handler) {
        ConMessage m = new ConMessage(true);
        m.setConnMessage("Conection Successful as Publisher");
        
        try {
            handler.sendMessage(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void acceptSubConnection(SubscriberHandler handler) {
        ConMessage m = new ConMessage(true);
        String newId = java.time.LocalDateTime.now().toString();
        handler.setId(newId);
        m.setConnMessage("Conection Successful as Subscriber");
        
        try {
            handler.sendMessage(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void denyPubConnection(PublisherHandler handler) {
        ConMessage m = new ConMessage(false);
        m.setConnMessage("Conection Denied as Publisher");
        
        try {
            handler.sendMessage(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void denySubConnection(SubscriberHandler handler) {
        ConMessage m = new ConMessage(false);
        m.setConnMessage("Conection Denied as Subscriber");
        
        try {
            handler.sendMessage(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
   }
    
    public static void main(String[] args) throws InterruptedException {
        try {
            GameServer server = new GameServer();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
