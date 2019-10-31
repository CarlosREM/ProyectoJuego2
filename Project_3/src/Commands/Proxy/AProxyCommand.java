/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.Proxy;

import Commands.ICommand;

/**
 *
 * @author Diego Murillo
 */
public abstract class AProxyCommand implements ICommand{
    private boolean on = true;
    protected ICommand command;
    
    public AProxyCommand (ICommand command){
        this.command = command;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
