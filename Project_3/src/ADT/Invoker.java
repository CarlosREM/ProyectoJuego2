/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import Abstraction.ICommand;
import Commands.CommandManager;

/**
 *
 * @author Marco Gamboa
 */
public class Invoker {
    public void execute(String text){
        String[] tokens = text.split(" -");
        ICommand c = new CommandManager().getCommand(tokens[0]);
        c.execute(text);
    }
}
