package com.anton.models.forms;

import com.anton.commandLine.*;
import com.anton.exeptions.ExceptionInFileMode;
import com.anton.models.Climate;


import java.util.Locale;

/**
 * Form for choose Climate
 */

public class ClimateForm extends AbstractForm<Climate> {
    private final Printable console;
    private final UserInput scanner;

    public ClimateForm(Printable console){
        this.console=(Console.isFileMode())
                ?new BlankConsole()
                :console;
        this.scanner=(Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Create new Enum element
     * @return enum object {@link Climate}
     */
    @Override
    public Climate build() {
        console.println("Виды климата: ");
        console.println(Climate.names());
        while (true){
            console.println("Введите тип климата: ");
            String input= scanner.nextLine().trim();
            try {
                //проверка
                if ("".equals(input)){
                    return null;
                }
                else {

                    return Climate.valueOf(input.toUpperCase(Locale.ROOT));}
            } catch (IllegalArgumentException e){
                console.printError("Такого климата нет в списке");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
