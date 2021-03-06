/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import ADT.ExtendedDefaultCharacter;
import ADT.ExtendedDefaultWeapon;
import SocketsImpl.Messages.AttackMessage;
import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public class AttactCommand implements ICommand {

    @Override
    public void execute(String[] text, Player player) {
        try {
            ExtendedDefaultCharacter edc = player.getWarriors().stream().filter(warr -> warr.getName().equals(text[1])).findAny().orElse(null);
            ExtendedDefaultWeapon weapon = (ExtendedDefaultWeapon) edc.getWeapons().stream().filter(weap -> weap.getName().equals(text[2])).findAny().orElse(null);

            if (weapon == null) {
                player.getClient().putResultText("ERROR: Weapon not found");
            } else {
                if (edc.getCurrentHealthPoints() > 0) {
                    player.attack(text[1], text[2]);
                } else {
                    player.getClient().putResultText("ERROR: The character is dead");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            player.getClient().putResultText("ERROR: in command");
        }
    }

}
