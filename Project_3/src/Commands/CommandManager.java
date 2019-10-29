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
    private static CommandManager commandManager;

    private CommandManager() {
        commands.put("Attack", new AttactCommand());
        commands.put("Chat", new ChatCommand());
        commands.put("Pass", new PassCommand());
        commands.put("AttackPlus", new AttackPlusCommand());
        commands.put("Draw", new MutaulSurrenderCommand());
        commands.put("Giveup", new SurrenderCommand());
        commands.put("Select", new SelectCommand());
    }

    public ICommand getCommand(String c) {
        if (commands.containsKey(c)) {
            return commands.get(c);
        } else {
            return new DefaultCommand();
        }
    }

    public void registCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    public static CommandManager getInstance() {
        if (commandManager == null) {
            commandManager = new CommandManager();
        }
        return commandManager;
    }
}
