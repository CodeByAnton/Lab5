package main.java.managers;

import main.java.commandLine.commands.AbstractCommand;

import java.io.FileNotFoundException;
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
     * @throws NonexistCommandException this command don't exist
     * @throws WrongArgumentsException wrong arguments of command
     * @throws CommandRuntimeException command throw mistake during execution
     * @throws ExitException command caused exit
     */

    public void execute(String name,String args) throws NonexistCommandException,WrongArgumentsException, CommandRuntimeException, ExitException{
        AbstractCommand command=commands.get(name);
        if (command==null) throw new NonexistCommandException();
        try {
            command.execute(args);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
