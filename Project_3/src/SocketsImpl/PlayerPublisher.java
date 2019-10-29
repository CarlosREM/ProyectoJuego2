/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl;

import SocketsImpl.Messages.ConMessage;
import SocketsImpl.Messages.RequestMessage;
import commsapi.Message.AMessage;
import commsapi.Publisher.APublisher;
import java.io.IOException;

/**
 *
 * @author Diego Murillo
 */
public class PlayerPublisher extends APublisher {

    Player player;

    public PlayerPublisher(String topic, Player player) throws IOException {
        super(topic);
        this.player = player;
    }

    public PlayerPublisher(String topic, String host, int port, Player player) throws IOException {
        super(topic, host, port);
        this.player = player;
    }

    @Override
    public void publish(AMessage message) {
        try {
            this.intermediate.sendMessage(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void receivedMessage(AMessage message) {
        System.out.println("message recieved on publisher " + message.serialize());

        if (message instanceof ConMessage) {
            ConMessage m = (ConMessage) message;
            this.setConnected(m.isAcceptedConnection());
            System.out.println(m.getConnMessage());
        }

        if (message instanceof RequestMessage) {
            RequestMessage rm = (RequestMessage) message;
            switch (rm.getRequestId()) {
                case 10:
                    this.player.getClient().lblAttackPlus.
                            setVisible(rm.getRequestString().equals("true"));
                    break;
                case 20:
                    this.player.getClient().fillMyStatus(rm.getRequestString());
                    break;
            }
        }
    }

}
