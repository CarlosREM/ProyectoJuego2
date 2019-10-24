/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commsapi.Message.AMessage;
import commsapi.Message.JsonManagement.InterfaceAdapter;

/**
 *
 * @author Diego Murillo
 */
public class ConMessage extends AMessage{
    private boolean acceptedConnection;
    private String connMessage;
    
    public ConMessage(boolean accepted){
        super("Connection");
        this.acceptedConnection = accepted;
    }

    public boolean isAcceptedConnection() {
        return acceptedConnection;
    }

    public void setAcceptedConnection(boolean acceptedConnection) {
        this.acceptedConnection = acceptedConnection;
    }

   

    public String getConnMessage() {
        return connMessage;
    }

    public void setConnMessage(String connMessage) {
        this.connMessage = connMessage;
    }

}
