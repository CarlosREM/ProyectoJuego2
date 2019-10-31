/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import Commands.ICommand;
import SocketsImpl.Player;

/**
 *
 * @author Diego Murillo
 */
public class AttackPlusProxyCommand extends AProxyCommand{
    
    public AttackPlusProxyCommand(ICommand command){
        super(command);
        
    }
    
    @Override
    public void execute(String[] text, Player player) {
        String timeStamp = java.time.LocalDateTime.now().toString();
        
        this.command.execute(text, player);
        
        String log = timeStamp + "\nComando: " + text[0]; 
        if (text.length >= 5){
            if(text.length == 4){
                log = log + " Parametros:\nAtaca: " + text[1] + " Arma 1: " + text[2] + " Arma 2: " + text[3] + 
                         
                    "\nResultado: " + checkCorrectness1(player, text);
            }
            if(text.length >= 5){
                log = log + " Parametros:\nAtaca 1: " + text[1] + " Arma : " + text[2] + " Ataca 2: " + text[3] + 
                        " Arma: " + text[4]  + 
                    "\nResultado: " + checkCorrectness2(player, text);
            }
        }  
        else{
            log = log + "\nResultado: Error de parámetros, mensaje no enviado al servidor";
        }
        
        log = log + "\n\n";
        player.addToMatchLog(log);
    }
    
    private String checkCorrectness1 (Player player, String[] text){
        for (ExtendedDefaultCharacter warrior : player.getWarriors()) {
            if (warrior.getName().equals(text[1])) {
                if (warrior.getCurrentHealthPoints() == 0)
                    return "Warrior derrotado, no se procesa el comando";
                
                ExtendedDefaultWeapon weapon1 = (ExtendedDefaultWeapon) warrior.getWeapons().stream().filter(weap -> weap.getName().equals(text[2])).findAny().orElse(null);
                ExtendedDefaultWeapon weapon2 = (ExtendedDefaultWeapon) warrior.getWeapons().stream().filter(weap -> weap.getName().equals(text[3])).findAny().orElse(null);

                if(weapon1 == null || weapon2 == null)
                    return "Arma inválida, no se procesa el comando";
                
                if(!weapon1.isAvailable() || !weapon2.isAvailable())
                    return "Arma no disponible, no se procesa el comando";
                
                return "Mensaje de ataque plus con dos armas enviado al servidor para procesamiento";
                
            }
        }
        return "Warrior inválido, no se procesa el comando";
    }
    
    private String checkCorrectness2 (Player player, String[] text){
        ExtendedDefaultCharacter char1 = (ExtendedDefaultCharacter) player.getWarriors().stream().filter(charac -> charac.getName().equals(text[1])).findAny().orElse(null);
        ExtendedDefaultCharacter char2 = (ExtendedDefaultCharacter) player.getWarriors().stream().filter(charac -> charac.getName().equals(text[3])).findAny().orElse(null);

        if(char1 == null || char2 == null)
            return "Warrior inválido, no se procesa el comando";
        if(char1.getCurrentHealthPoints() == 0 || char2.getCurrentHealthPoints() == 0)
            return "Warrior derrotado, no se procesa el comando";
        
        ExtendedDefaultWeapon weapon1 = (ExtendedDefaultWeapon) char1.getWeapons().stream().filter(weap -> weap.getName().equals(text[2])).findAny().orElse(null);
        ExtendedDefaultWeapon weapon2 = (ExtendedDefaultWeapon) char2.getWeapons().stream().filter(weap -> weap.getName().equals(text[4])).findAny().orElse(null);

        if(weapon1 == null || weapon2 == null)
            return "Arma inválida, no se procesa el comando";
        
        if(!weapon1.isAvailable() || !weapon2.isAvailable())
            return "Arma no disponible, no se procesa el comando";
        
        return "Mensaje de ataque plus con dos warriors enviado al servidor para procesamiento";
    }
}
