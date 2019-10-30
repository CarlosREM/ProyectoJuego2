/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import Abstraction.ICommand;
import SocketsImpl.Player;
import abstraction.AWeapon;

/**
 *
 * @author Marco Gamboa
 */
public class ReloadCommand implements ICommand{

    @Override
    public void execute(String[] text,Player player) {
        try{
            if(!hasWeapons(player)){
                reloadWeapons(player);
                player.getClient().putResultText("Weapons reloaded!");
            }
            else{
                player.getClient().putResultText("ERROR: You've got available weapons");
            }
        } catch (IndexOutOfBoundsException e) {
            player.getClient().putResultText("ERROR: in command");
        }
    }
    
    private boolean hasWeapons(Player player){
        for(ExtendedDefaultCharacter edc : player.getWarriors()){
            for(AWeapon aw :edc.getWeapons()){
                ExtendedDefaultWeapon edw = (ExtendedDefaultWeapon) aw;
                if(edw.isAvailable()){
                    return true;
                }
            }
        }
        return false;
    }    
    private void reloadWeapons(Player player){
        for(ExtendedDefaultCharacter edc : player.getWarriors()){
            for(AWeapon aw :edc.getWeapons()){
                ExtendedDefaultWeapon edw = (ExtendedDefaultWeapon) aw;
                edw.setAvailable(true);
            }
        }
    }
}
