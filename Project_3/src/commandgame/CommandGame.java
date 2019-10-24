/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandgame;

import ADT.Invoker;
import Abstraction.ICommand;
import SocketsImpl.Player;
import View.ActionWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco Gamboa
 */
public class CommandGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ActionWindow window = new ActionWindow();
        
        window.setVisible(true);

    }
    
}
