package main.java.commandLine.commands;

import main.java.exeptions.ExitExeption;

/**
 * Interface for executable command
 */

public interface Executable {
    public void execute(String args) throws ClassCastException, ExitExeption, IllegalArgumentException;
}
