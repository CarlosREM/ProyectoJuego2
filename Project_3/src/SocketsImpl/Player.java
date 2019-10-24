/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import ADT.ExtendedDefaultCharacter;
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
    
    public Player(String id, List<ExtendedDefaultCharacter> warriors) throws IOException{
        publisher = new PlayerPublisher(id, this);
        subscriber = new PlayerSubscriber(id, this);
        this.warriors = warriors;
    }
    
    public Player(String id, String host, int port, List<ExtendedDefaultCharacter> warriors) throws IOException{
        publisher = new PlayerPublisher(id, host, port, this);
        subscriber = new PlayerSubscriber(id, host, port, this);
        this.warriors = warriors;
    }
    
    public List<ExtendedDefaultCharacter> getWarriors(){
        return warriors;
    }
    
    public void setWarriors(List<ExtendedDefaultCharacter> warriors){
        this.warriors = warriors;
    }
        
}
