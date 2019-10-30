/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import Commands.Proxy.AProxyCommand;
import Commands.Proxy.ChatProxyCommand;
import java.util.HashMap;

/**
 *
 * @author Marco Gamboa
 */
public class CommandManager {

    private final HashMap<String, AProxyCommand> commands = new HashMap<>();
    private static CommandManager commandManager;

    private CommandManager() {
        commands.put("Attack", new AttactCommand());
        commands.put("Chat", new ChatProxyCommand(new ChatCommand()));
        commands.put("Pass", new PassCommand());
        commands.put("AttackPlus", new AttackPlusCommand());
        commands.put("Draw", new MutualSurrenderCommand());
        commands.put("Giveup", new SurrenderCommand());
        commands.put("Select", new SelectCommand());
        commands.put("Reload", new ReloadCommand());
        commands.put("Y", new OptionCommand());
        commands.put("N", new OptionCommand());
    }

    public ICommand getCommand(String c) {
        if (commands.containsKey(c)) {
            return commands.get(c);
        } else {
            return new DefaultCommand();
        }
    }

    public void registCommand(String name, AProxyCommand command) {
        commands.put(name, command);
    }

    public static CommandManager getInstance() {
        if (commandManager == null) {
            commandManager = new CommandManager();
        }
        return commandManager;
    }
    
    public void setProxyState(boolean state){
        for(String key : commands.keySet()){
            this.commands.get(key).setOn(state);
        }
    }
    
   
}
