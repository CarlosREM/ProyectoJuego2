/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import SocketsImpl.Messages.AttackMessage;
import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public class AttackPlusCommand implements ICommand {

    @Override
    public void execute(String[] text, Player player) {
        if (player.getClient().lblAttackPlus.isVisible()) {

            try {
                if (text.length == 6) {
                    player.attackExtraChar(text[1], text[2], text[3], text[4], text[5]);
                } else {
                    player.attackExtraWeapon(text[1], text[2], text[3], text[4]);
                }
            } catch (IndexOutOfBoundsException e) {
                player.getClient().putResultText("ERROR: in command");
            }
        } else {
            player.getClient().putResultText("AttackPlus is not available");

        }
    }

}
