/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import SocketsImpl.Player;

/**
 *
 * @author Marco Gamboa
 */
public interface ICommand {
    public void execute(String[] text,Player player);
}
