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
public class SelectCommand implements ICommand {

    @Override
    public void execute(String[] text, Player player) {
        try{
            player.getClient().selectChar(text[1]);
        } catch (IndexOutOfBoundsException e) {
            player.getClient().putResultText("ERROR: in command");
        }
    }

}
