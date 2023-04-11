package main.java.commandLine.commands;

import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;

public class Exit extends AbstractCommand{
    public Exit(){
        super("exit",": завершить программу");

    }

    @Override
    public void execute(String args) throws ExitException {
        throw new ExitException();
    }
}
