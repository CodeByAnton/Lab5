package main.java.models.froms;

import main.java.commandLine.*;
import main.java.exeptions.ExceptionInFileMode;

import main.java.models.StandardOfLiving;

/**
 * Form for standardofliving
 */

public class StandardOfLivingForm extends AbstractForm<StandardOfLiving>{
    private final Printable console;
    private final UserInput scanner;

    public StandardOfLivingForm(Printable console){
        this.console=(Console.isFileMode())
                ?new BlankConsole()
                :console;
        this.scanner=(Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Create new Enum element
     * @return enum object {@link StandardOfLiving}
     */

    @Override
    public StandardOfLiving build() {
        console.println("Стадарты жизни: ");
        console.println(StandardOfLiving.names());
        while (true) {
            console.println("Введите стандарт жизни: " );
            String input = scanner.nextLine().trim();
            try {
                return StandardOfLiving.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                console.printError("Такого стандарта жизни нет в списке");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
