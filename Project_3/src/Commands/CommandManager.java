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
            case "atacar":
                return new AttactCommand();   
            case "chat":
                return new ChatCommand();
            case "pasar":
                return new PassCommand();                
            case "comodin":
                return new ExtraCharCommand();
            case "empate":
                return new MutaulSurrenderCommand();
            case "rendirse":
                return new SurrenderCommand();
            default:
                return null;
        }
    }
    
    public void registCommand(String name, ICommand command){
        commands.put(name,command);
    }
}