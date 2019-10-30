/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import ADT.Statistics;
import SocketsImpl.Messages.AttackMessage;
import SocketsImpl.Messages.AttackPlusMessage;
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
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Murillo
 */
public class GameServer extends AContentServer {

   
    private HashMap<String, Statistics> statisticsMap;
    private HashMap<String, StringBuilder> logsMap;
    private TimedEventTrigger plusTrigger;

    public GameServer() throws IOException {
        super();
        System.out.println("SERVER running");
        System.out.println(InetAddress.getLocalHost());
        this.statisticsMap = new HashMap<>();
        this.logsMap = new HashMap();
        this.plusTrigger = new TimedEventTrigger(this);
        this.plusTrigger.start();
    }

    @Override
    public void processSubMessage(AMessage message, SubscriberHandler handler) {
        if (message instanceof RequestMessage) {
            try {
                RequestMessage m = (RequestMessage) message;
                switch (m.getRequestId()) {
                    case 0: //Asked for available players
                        broadcastTopics(handler);
                        break;

                    case 1: //Initiate Duel
                        String topic = m.getRequestString();
                        SubscriberHandler oponent = this.subscribers.stream().filter(sub -> sub.getId().equals(topic)).findAny().orElse(null);

                        this.registerSubscription(topic, handler);
                        this.registerSubscription(handler.getId(), oponent);
                        
                        String matchStart = java.time.LocalDateTime.now().toString();

                        RequestMessage m2 = new RequestMessage();
                        m2.setRequestId(777);
                        m2.setRequestString("Duel Start! " + handler.getId() + " vs " + topic);
                        m2.setTopic(matchStart);
                        oponent.sendMessage(m2);
                        handler.sendMessage(m2);
                        
                        StringBuilder newMatchLog = new StringBuilder();
                        newMatchLog.append("Match started at " + matchStart);
                        newMatchLog.append("Oponents: " + topic + ", " + handler.getId());
                        
                        this.logsMap.put(matchStart, newMatchLog);

                        break;
                    case 2://unsubscribe
                        String topic2 = m.getRequestString();
                        SubscriberHandler oponent2 = this.subscribers.stream().filter(sub -> sub.getId().equals(topic2)).findAny().orElse(null);

                        this.removeSubscription(topic2, handler);
                        this.removeSubscription(handler.getId(), oponent2);
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
        if (message instanceof DuelStateMessage) {
            DuelStateMessage dm = (DuelStateMessage) message;
            try {
                dm.setRivalInfo(statisticsMap.get(dm.getTopic()).toString());
                this.broadcastMessageSub(dm, handler.getTopic());
                
            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (message instanceof RequestMessage) {
            RequestMessage rm = (RequestMessage) message;
            switch (rm.getRequestId()) {
                case 50: {//send a chat message
                    try {
                        rm.setRequestString("#" + handler.getTopic() + " says: "
                                + rm.getRequestString());
                        this.broadcastMessageSub(rm, handler.getTopic());
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 51: {//surrender
                    try {
                        this.statisticsMap.get(handler.getTopic()).addDefeat();
                        this.statisticsMap.get(handler.getTopic()).addGiveup();
                        this.broadcastMessageSub(rm, handler.getTopic());
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 52: {//attack result
                    try {
                        String scores = this.statisticsMap.get(subscriptions.
                                get(handler.getTopic()).get(0).getId()).toString();
                        rm.setRequestString(rm.getRequestString()+";"+scores);
                        this.broadcastMessageSub(rm, handler.getTopic());
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 53: {//succes or fail attack
                
                       if(rm.getRequestString().equals("succes")){
                           statisticsMap.get(rm.getTopic()).addSuccess();
                       }else{
                           statisticsMap.get(rm.getTopic()).addFailed();
                       }               
                    break;
                }                
                case 60: {//pass

                    try {
                        this.broadcastMessageSub(rm, handler.getTopic());
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 100: {//asking for draw
                    
                    try {
                        this.broadcastMessageSub(rm, handler.getTopic());
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
               case 101: {//answering
                    
                    if(rm.getRequestString().equals("Y")){
                        statisticsMap.get(rm.getTopic()).addDraw();
                        statisticsMap.get(subscriptions.
                                get(handler.getTopic()).get(0).getId()).addDraw();                        
                    }
                    try {

                        this.broadcastMessageSub(rm, handler.getTopic());
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
               }                
                case 77: {//add to log
                    this.logsMap.get(rm.getTopic()).append(rm.getRequestString());
                    System.out.println("Added to log: " + rm.getTopic() + "\n" + rm.getRequestString());
                    break;
                }
                case -666: {//disconnect
                    PublisherHandler pubD = this.publishers.stream().filter(pub -> pub.getTopic().equals(handler.getTopic())).findAny().orElse(null);
                    SubscriberHandler subD = this.subscribers.stream().filter(sub -> sub.getId().equals(handler.getTopic())).findAny().orElse(null);

                    removePublisher(pubD);
                    removeSubscriber(subD);
                    pubD.closeConn();
                    subD.closeConn();
                    
                    break;
                }
            }
        }
        if (message instanceof AttackMessage) {
            AttackMessage am = (AttackMessage) message;
            this.statisticsMap.get(handler.getTopic()).addAttack();
            System.out.println("flaggggggg");
            try {
                this.broadcastMessageSub(am, handler.getTopic());
            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (message instanceof AttackPlusMessage) {
            AttackPlusMessage apm = (AttackPlusMessage) message;
             this.statisticsMap.get(handler.getTopic()).addAttack();
            try {
                this.broadcastMessageSub(apm.getAttackMsg1(), handler.getTopic());
                this.broadcastMessageSub(apm.getAttackMsg2(), handler.getTopic());

            } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void broadcastTopics(SubscriberHandler handler) {
        TopicsMessage m = new TopicsMessage();
        for (String key : this.subscriptions.keySet()) {
            if (this.subscriptions.get(key).isEmpty() && !key.equals(handler.getId())) {
                m.getTopics().add(key);
            }
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
        RequestMessage rm = new RequestMessage();
        if (!statisticsMap.containsKey(handler.getTopic())) {
            statisticsMap.put(handler.getTopic(), new Statistics());
        }
        rm.setRequestString(statisticsMap.get(handler.getTopic()).toString());
        rm.setRequestId(20);
        try {
            handler.sendMessage(m);
            handler.sendMessage(rm);
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
    
    public void chooseWinner(){
        Random r = new Random();
        RequestMessage rm2 = new RequestMessage();
        rm2.setRequestId(10);
        rm2.setRequestString("true");
        
        for (String key : this.subscriptions.keySet()) {
            if (!this.subscriptions.get(key).isEmpty()) {
                if(r.nextBoolean())
                {
                    try {
                        PublisherHandler lucky = this.publishers.stream().filter(pub -> pub.getTopic().equals(key)).findAny().orElse(null);
                        lucky.sendMessage(rm2);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return;
                }
            }
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
