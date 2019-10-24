/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import java.util.HashMap;

/**
 *
 * @author Marco Gamboa
 */
public class CommandManager {
    private static final HashMap<String, ICommand> commands = new HashMap<>();
    

    public ICommand getCommand(String c){
        switch(c){
            case "Attack":
                return new AttactCommand();   
            case "Chat":
                return new ChatCommand();
            case "Pass":
                return new PassCommand();                
            case "AttackPlus":
                return new ExtraCharCommand();
            case "Draw":
                return new MutaulSurrenderCommand();
            case "Giveup":
                return new SurrenderCommand();
            default:
                return null;
        }
    }
    
    public void registCommand(String name, ICommand command){
        commands.put(name,command);
    }
}
