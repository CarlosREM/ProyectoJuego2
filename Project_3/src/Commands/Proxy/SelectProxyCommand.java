/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import Commands.ICommand;
import SocketsImpl.Player;

/**
 *
 * @author Diego Murillo
 */
public class SelectProxyCommand extends AProxyCommand{
    public SelectProxyCommand(ICommand command){
        super(command);
    }
    
    @Override
    public void execute(String[] text, Player player) {
        String timeStamp = java.time.LocalDateTime.now().toString();
        
        this.command.execute(text, player);
        
        String log = timeStamp + "\nComando: " + text[0]; 
        if (text.length >= 1){
            log = log + " Parametros: " + text[1] + "\nResultado: Warrior seleccionado en pantalla";
        }  
        else{
            log = log + "\nResultado: Error de parámetros, mensaje no enviado al servidor";
        }
        
        log = log + "\n\n";
        player.addToMatchLog(log);
    }
}
