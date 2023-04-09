package main.java.managers;

import main.java.commandLine.commands.AbstractCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import main.java.exeptions.*;

/**
 * Command Manager
 * Use command pattern
 */
public class CommandManager {
    /**
     * Field to keep command:Name-command
     */
    private final HashMap<String, AbstractCommand> commands=new HashMap<>();
    /**
     * Field for history command
     */
    private final List<String> commandHistory=new ArrayList<>();
    public void addCommand(Collection<AbstractCommand> commands){
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(AbstractCommand::getName,s->s)));
    }
    public Collection<AbstractCommand> getCommands(){
        return commands.values();
    }
    public void addToHistory(String line){
        if(line.isBlank()) return;
        this.commandHistory.add(line);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     *
     * @param name command
     * @param args of command
     * @throws NonexistCommandExeption this command don't exist
     * @throws WrongArgumentsExeption wrong arguments of command
     * @throws CommandRuntimeExeption command throw mistake during execution
     * @throws ExitExeption command caused exit
     */

    public void execute(String name,String args) throws NonexistCommandExeption,WrongArgumentsExeption, CommandRuntimeExeption, ExitExeption{
        AbstractCommand command=commands.get(name);
        if (command==null) throw new NonexistCommandExeption();
        command.execute(args);
    }
}
