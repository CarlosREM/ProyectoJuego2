/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import commsapi.ContentServer.AContentServer;
import commsapi.Message.AMessage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Murillo
 */
public class TimedEventTrigger extends Thread{
    private GameServer server;
    private Random random;
    
    public TimedEventTrigger (GameServer server){
        super();
        this.server = server;
        random = new Random();
    }
    
    @Override
    public void run() {
        
            while (true) {               
                try {
                    sleep(10000);
                    
                    if(random.nextBoolean())
                        this.server.chooseWinner();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimedEventTrigger.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }
}
