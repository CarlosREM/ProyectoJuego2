/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import ADT.ExtendedDefaultCharacter;
import View.ActionWindow;
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
    
    
    
    public Player(String id, List<ExtendedDefaultCharacter> warriors, ActionWindow client) throws IOException{
        publisher = new PlayerPublisher(id, this);
        subscriber = new PlayerSubscriber(id, this);
        this.warriors = warriors;
        this.client = client;
    }
    
    public Player(String id, String host, List<ExtendedDefaultCharacter> warriors, ActionWindow client) throws IOException{
        int defaultPubPort = Integer.parseInt(PropertiesUtil.getInstance().getProperty("defaultPubPort"));
        int defaultSubPort = Integer.parseInt(PropertiesUtil.getInstance().getProperty("defaultSubPort"));
        publisher = new PlayerPublisher(id, host, defaultPubPort, this);
        subscriber = new PlayerSubscriber(id, host, defaultSubPort, this);
        this.warriors = warriors;
        this.client = client;
    }
    
    public List<ExtendedDefaultCharacter> getWarriors(){
        return warriors;
    }
    
    public void setWarriors(List<ExtendedDefaultCharacter> warriors){
        this.warriors = warriors;
    }
        
}