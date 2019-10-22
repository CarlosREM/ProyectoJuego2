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
}
