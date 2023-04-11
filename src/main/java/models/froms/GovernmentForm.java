package main.java.models.froms;

import main.java.commandLine.*;
import main.java.exeptions.ExceptionInFileMode;

import main.java.models.Government;

/**
 * Form for government
 */

public class GovernmentForm extends AbstractForm<Government> {
    private final Printable console;
    private final UserInput scanner;

    public GovernmentForm(Printable console){
        this.console=(Console.isFileMode())
                ?new BlankConsole()
                :console;
        this.scanner=(Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Create new Enum element
     * @return enum object {@link Government}
     */

    @Override
    public Government build() {
        console.println("Виды правительства: ");
        console.println(Government.names());
        while (true){
            console.println("Введите тип правительства: " );
            String input= scanner.nextLine().trim();
            try {
                return Government.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e){
                console.printError("Такого правительства нет в списке");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}

