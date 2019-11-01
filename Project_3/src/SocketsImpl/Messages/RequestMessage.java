/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsImpl.Messages;

import commsapi.Message.AMessage;

/**
 *
 * @author Diego Murillo
 */
public class RequestMessage extends AMessage{
    private int RequestId;
    private String RequestString;
    private int type;
    
    public RequestMessage(){
        super("Request");
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int RequestId) {
        this.RequestId = RequestId;
    }

    public String getRequestString() {
        return RequestString;
    }

    public void setRequestString(String RequestString) {
        this.RequestString = RequestString;
    }
    
    
}
