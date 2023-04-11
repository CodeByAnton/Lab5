package main.java.commandLine.commands;

import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;


/**
 * Interface for executable command
 */

public interface Executable {
     void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException;
}
