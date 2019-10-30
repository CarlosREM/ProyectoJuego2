/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import Abstraction.ICommand;
import SocketsImpl.Player;

/**
 *
 * @author Diego Murillo
 */
public class AttackProxyCommand extends AProxyCommand{
    public AttackProxyCommand(ICommand command){
        super(command);
        
    }
    
    @Override
    public void execute(String[] text, Player player) {
        String timeStamp = java.time.LocalDateTime.now().toString();
        
        this.command.execute(text, player);
        
        String log = timeStamp + "\nComando: " + text[0]; 
        if (text.length >= 3){
            log = log + " Parametros:\nAtaca: " + text[1] + " Arma: " + text[2] + 
                    "\nResultado: " + checkCorrectness(player, text);
        }  
        else{
            log = log + "\nResultado: Error de parámetros, mensaje no enviado al servidor";
        }
        System.out.println("");
        log = log + "\n\n";
        player.addToMatchLog(log);
    }
    
    private String checkCorrectness (Player player, String[] text){
        for (ExtendedDefaultCharacter warrior : player.getWarriors()) {
            if (warrior.getName().equals(text[1])) {
                if (warrior.getCurrentHealthPoints() == 0)
                    return "Warrior derrotado, no se procesa el comando";
                
                for (int i = 0; i < warrior.getWeapons().size(); i++) {
                    if (warrior.getWeapons().get(i).getName().equals(text[2])) {
                        ExtendedDefaultWeapon ew = (ExtendedDefaultWeapon) warrior.getWeapons().get(i);
                        if (ew.isAvailable()) {
                            return "Mensaje de ataque enviado al servidor para procesamiento";
                        } else {
                            return "Arma no disponible, no se procesa el comando";
                        }
                    }
                }
                return "Arma inválida, no se procesa el comando";
            }
        }
        return "Warrior inválido, no se procesa el comando";
    }
    
}
