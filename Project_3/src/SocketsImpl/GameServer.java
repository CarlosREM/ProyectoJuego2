/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import SocketsImpl.Messages.ConMessage;
import commsapi.ContentServer.AContentServer;
import commsapi.ContentServer.PublisherHandler;
import commsapi.ContentServer.SubscriberHandler;
import commsapi.Message.AMessage;
import java.io.IOException;
import java.net.InetAddress;

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
    public void processSubMessage(AMessage arg0, SubscriberHandler arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processPubMessage(AMessage arg0, PublisherHandler arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void broadcastTopics(SubscriberHandler arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void acceptSubConnection(SubscriberHandler arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
