package main.java.commandLine.commands;

import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;

import java.io.FileNotFoundException;


/**
 * Interface for executable command
 */

public interface Executable {
     void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException, FileNotFoundException;
}
