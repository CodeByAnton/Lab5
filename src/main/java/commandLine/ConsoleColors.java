package main.java.commandLine;

/**
 * Enum for coloring the output
 */
public enum ConsoleColors {
    RED ("\u001B[30m"),
    RESET("\u001B[0m");
    private final String title;
    ConsoleColors(String title){
        this.title=title;
    }

    /**
     * Method for coloring output
     * @param s line for coloring
     * @param color
     * @return colored line
     */
    public static String toColor(String s,ConsoleColors color){
        return color+s+ConsoleColors.RESET;
    }
    @Override
    public String toString(){
        return this.title;
    }

}
