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
public class DefaultCommand implements ICommand{

    @Override
    public void execute(String[] text,Player player) {
        player.getClient().putResultText("Unknow command +"+text[0]+", valid commands:\n"
                + "Chat\nAttack\nPass\nAttackPlus\nDraw\nGiveup");
    }
    
}
