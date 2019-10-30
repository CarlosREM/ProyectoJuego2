/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import SocketsImpl.Messages.RequestMessage;
import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public class OptionCommand implements ICommand{
    
    @Override
    public void execute(String[] text,Player player) {
            RequestMessage rm = new RequestMessage();
            rm.setRequestString(text[0]);
            rm.setTopic(player.getTopic());
            rm.setRequestId(101);
            player.publish(rm);
            if(text[0].equals("Y")){
               player.win("");
            }else{
                player.getClient().setEnableCmd(false);
            }
            
    }
    
}
