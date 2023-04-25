package com.anton.managers;

import com.anton.exeptions.EmptyCollectionException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.InvalidFormException;
import com.anton.models.City;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.StreamException;
import com.anton.commandLine.Console;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Class for work with file
 */

public class FileManager {
    private String text;
    private final Console console;
    private final CollectionManager collectionManager;
    private final String filePath;


    private final XStream xStream=new XStream();

    /**
     * Create alias for XStream
     * @param console User input/output
     * @param collectionManager Work with collection
     * @param filePath Path to xml file
     */
    public FileManager(Console console, CollectionManager collectionManager, String filePath){
        this.console=console;
        this.filePath=filePath;
        this.collectionManager=collectionManager;
        this.xStream.alias("City", City.class);
        this.xStream.alias("HashSet",CollectionManager.class);
        this.xStream.addPermission(AnyTypePermission.ANY);
        this.xStream.addImplicitCollection(CollectionManager.class,"collection");

    }



    /**
     * Read collection from file
     */

    public void findFile() throws ExitException {
        File file = new File(filePath);
        if (filePath==null || filePath.isEmpty()){
            console.printError("Путь должен передаваться при помощи аргумента командной строки");
            throw new ExitException();
        }
        else {
            console.println("Путь получен успешно");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            if (stringBuilder.isEmpty()) {
                console.printError("Файл пустой");
                this.text = "</HashSet>";
                return;
            }
            this.text = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            console.printError("Такого файла не найдено");
            throw new ExitException();
        } /*catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            throw new ExitException();
        }*/
    }


    /**
     * Create objects
     * @throws ExitException when objects not valid
     */
    public void createObjects() throws ExitException, NullPointerException {
        try{
            XStream xstream=new XStream();
            xstream.alias("City",City.class);
            xstream.alias("HashSet",CollectionManager.class);
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.addImplicitCollection(CollectionManager.class,"collection");
            CollectionManager collectionManagerWithObjects=(CollectionManager) xstream.fromXML(this.text);
            for (City city:collectionManagerWithObjects.getCollection()){
                if (this.collectionManager.checkExist(city.getId())){
                    console.printError("В файле повторяются id");
                    throw new ExitException();
                }
                this.collectionManager.addElement(city);


            }
            this.collectionManager.addElements(collectionManagerWithObjects.getCollection());

        }catch (InvalidFormException | StreamException | ConversionException | NullPointerException e){

            if (new File(filePath).length()!=0) {

                console.printError("Объекты в файле не валидны");
            }

        }
        City.updateId(collectionManager.getCollection());
    }

    /**
     * Save collection in file
     */
    public void saveObjects() throws EmptyCollectionException {
        if (collectionManager.getCollection().size() == 0) {
            throw new EmptyCollectionException();

        } else {

            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
                this.xStream.toXML(collectionManager, writer);
                writer.close();
            } catch (FileNotFoundException e) {
                console.printError("Файл не существует");
            } catch (IOException e) {
                console.printError("Ошибка ввода вывода");
            }
        }
    }
}
