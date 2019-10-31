/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import SocketsImpl.Messages.RequestMessage;
import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public class OptionCommand implements ICommand {

    @Override
    public void execute(String[] text, Player player) {
        RequestMessage rm = new RequestMessage();
        rm.setRequestString(text[0]);
        rm.setTopic(player.getTopic());
        rm.setRequestId(101);
        player.publish(rm);
        if (text[0].equals("Y")) {
            player.endGame("");
            RequestMessage rm4 = new RequestMessage();
            rm4.setRequestId(500);
            rm4.setRequestString(player.getTopic());
            rm4.setRequestString(player.matchStart);
            player.getClient().setEnableSearchPlayers(true);
            player.publish(rm4);
        } else {
            player.getClient().setEnableCmd(false);
        }

    }

}
