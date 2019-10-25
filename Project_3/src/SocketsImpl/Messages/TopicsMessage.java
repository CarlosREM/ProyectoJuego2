/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import commsapi.Message.AMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Diego Murillo
 */
public class TopicsMessage extends AMessage{
    private List<String> topics;
    
    public TopicsMessage(){
        super("Publishers");
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    
    
}
