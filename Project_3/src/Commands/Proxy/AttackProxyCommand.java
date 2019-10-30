/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import SocketsImpl.Player;

/**
 *
 * @author Diego Murillo
 */
public class AttackProxyCommand extends AProxyCommand{

    @Override
    public void execute(String[] text, Player player) {
        
        
        this.command.execute(text, player);
        
        
    }
    
}
