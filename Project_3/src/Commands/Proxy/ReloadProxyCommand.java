/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import Abstraction.ICommand;
import Commands.ReloadCommand;
import SocketsImpl.Player;

/**
 *
 * @author Diego Murillo
 */
public class ReloadProxyCommand extends AProxyCommand{
    public ReloadProxyCommand(ICommand command){
        super(command);
    }
    
    @Override
    public void execute(String[] text, Player player) {
        String timeStamp = java.time.LocalDateTime.now().toString();
        
        this.command.execute(text, player);
        
        ReloadCommand r = (ReloadCommand) this.command;
        String log = timeStamp + "\nComando: " + text[0]; 
        if (text.length >= 1){
            if(!r.hasWeapons(player)){
                log = log + " Parametros: ninguno" + "\nResultado: Armas recargadas";
            }
            else{
                log = log + " Parametros: ninguno" + "\nResultado: Todavia quedan armas disponibles, no se hizo recarga";
            }
            
        }  
        
        log = log + "\n\n";
        player.addToMatchLog(log);
    }
}
