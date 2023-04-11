package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CommandManager;

public class Help extends AbstractCommand{
    private CommandManager commandManager;
    private Console console;
    public Help(Console console,CommandManager commandManager){
        super("help",": вывести справку по доступным командам");
        this.commandManager=commandManager;
        this.console=console;
    }
    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        commandManager.getCommands().forEach(command->console.println(command.getName()+command.getDescripion()));

    }

}
