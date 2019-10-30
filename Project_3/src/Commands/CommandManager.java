/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Abstraction.ICommand;
import Commands.Proxy.AProxyCommand;
import Commands.Proxy.AttackPlusProxyCommand;
import Commands.Proxy.AttackProxyCommand;
import Commands.Proxy.ChatProxyCommand;
import Commands.Proxy.MutualSurrenderProxyCommand;
import Commands.Proxy.OptionProxyCommand;
import Commands.Proxy.PassProxyCommand;
import Commands.Proxy.ReloadProxyCommand;
import Commands.Proxy.SelectProxyCommand;
import Commands.Proxy.SurrenderProxyCommand;
import java.util.HashMap;

/**
 *
 * @author Marco Gamboa
 */
public class CommandManager {

    private final HashMap<String, AProxyCommand> commands = new HashMap<>();
    private static CommandManager commandManager;

    private CommandManager() {
        commands.put("Attack", new AttackProxyCommand(new AttactCommand()));
        commands.put("Chat", new ChatProxyCommand(new ChatCommand()));
        commands.put("Pass", new PassProxyCommand(new PassCommand()));
        commands.put("AttackPlus", new AttackPlusProxyCommand(new AttackPlusCommand()));
        commands.put("Draw", new MutualSurrenderProxyCommand(new MutualSurrenderCommand()));
        commands.put("Giveup", new SurrenderProxyCommand(new SurrenderCommand()));
        commands.put("Select", new SelectProxyCommand(new SelectCommand()));
        commands.put("Reload", new ReloadProxyCommand(new ReloadCommand()));
        commands.put("Y", new OptionProxyCommand(new OptionCommand()));
        commands.put("N", new OptionProxyCommand(new OptionCommand()));
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
