package com.anton.commandLine.commands;

import com.anton.exeptions.ExitException;


/**
 * Command that exit from program
 */
public class Exit extends AbstractCommand{
    public Exit(){
        super("exit",": завершить программу");

    }

    @Override
    public void execute(String args) throws ExitException {
        throw new ExitException();
    }
}
