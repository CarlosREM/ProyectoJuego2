/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public class AttactCommand implements ICommand{

    @Override
    public void execute(String[] text,Player player) {
        try{
            player.attack(text[1], text[2], text[3]);
        } catch (IndexOutOfBoundsException e) {
            player.getClient().putResultText("Error in command");
        }
    }
    
}
