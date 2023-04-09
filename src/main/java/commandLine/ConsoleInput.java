package main.java.commandLine;


import main.java.managers.ScannerManager;

import java.util.Scanner;

/**
 * Class for standard input via console
 */

public class ConsoleInput implements UserInput {
    private static final Scanner userInputScanner= ScannerManager.getUserInputScanner();

    @Override
    public String nextLine() {
        return userInputScanner.nextLine();
    }
}