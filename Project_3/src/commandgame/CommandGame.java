/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandgame;

import ADT.Invoker;
import Abstraction.ICommand;

/**
 *
 * @author Marco Gamboa
 */
public class CommandGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Invoker i = new Invoker();
        i.execute("atacar -esto es el texto");
    }
    
}
