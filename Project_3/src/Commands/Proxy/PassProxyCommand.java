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
public class PassProxyCommand extends AProxyCommand{
    public PassProxyCommand(ICommand command){
        super(command);
        
    }
    @Override
    public void execute(String[] text, Player player) {
        String timeStamp = java.time.LocalDateTime.now().toString();
        
        this.command.execute(text, player);
        
        String log = timeStamp + "\nComando: Option to Draw, Answer: " + text[0]; 
        if (text.length >= 1){
            log = log + " Parametros: ninguno" + "\nResultado: Mensaje de respuesta a empate enviado a servidor para redirección";
        }  
        
        log = log + "\n\n";
        player.addToMatchLog(log);
    }
    
}
