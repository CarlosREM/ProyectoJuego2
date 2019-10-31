/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import ADT.ExtendedDefaultCharacter;
import Abstraction.ICommand;
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
                if (text.length == 5) {
                    ExtendedDefaultCharacter edc = player.getWarriors().stream().filter(warr -> warr.getName().equals(text[1])).findAny().orElse(null);
                    ExtendedDefaultCharacter edc2 = player.getWarriors().stream().filter(warr -> warr.getName().equals(text[3])).findAny().orElse(null);
                    if (edc.getCurrentHealthPoints() > 0 && edc2.getCurrentHealthPoints() > 0) {
                        player.attackExtraChar(text[1], text[2], text[3], text[4]);
                    } else {
                        player.getClient().putResultText("ERROR: Some character is dead");
                    }
                } else {

                    ExtendedDefaultCharacter edc = player.getWarriors().stream().filter(warr -> warr.getName().equals(text[1])).findAny().orElse(null);
                    if (edc.getCurrentHealthPoints() > 0) {
                        player.attackExtraWeapon(text[1], text[2], text[3]);
                    } else {
                        player.getClient().putResultText("ERROR: Character is dead");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                player.getClient().putResultText("ERROR: in command");
            }
        } else {
            player.getClient().putResultText("AttackPlus is not available");

        }
    }

}
