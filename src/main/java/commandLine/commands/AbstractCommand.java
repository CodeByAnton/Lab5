package main.java.commandLine.commands;

/**
 * Abstract class for all command
 */
public abstract class AbstractCommand implements Executable{
    private final String name;
    private final String descripion;
    public AbstractCommand(String name,String descripion){
        this.name=name;
        this.descripion=descripion;
    }
    public String getName(){
        return name;
    }
    public String getDescripion(){
        return descripion;
    }
    @Override
    public String toString(){
        return "name: "+this.name+"\n"+
                "description: "+this.descripion;
    }

}
