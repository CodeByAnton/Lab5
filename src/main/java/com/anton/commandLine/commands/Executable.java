package com.anton.commandLine.commands;

import com.anton.exeptions.CommandRuntimeException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.WrongArgumentsException;


import java.io.FileNotFoundException;


/**
 * Interface for executable command
 */

public interface Executable {
     void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException, FileNotFoundException;
}
