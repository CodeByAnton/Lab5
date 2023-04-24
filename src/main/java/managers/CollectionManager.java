package main.java.managers;

import main.java.exeptions.ExitException;
import main.java.exeptions.InvalidFormException;
import main.java.models.City;


import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

import java.util.HashSet;

/**
 * Class for work with collection
 */
public class CollectionManager {
    private final HashSet<City> collection=new HashSet<>();
    private LocalDateTime lastInitTime;

    public CollectionManager(){
        this.lastInitTime=LocalDateTime.now();
    }
    public HashSet<City> getCollection(){
        return collection;
    }
    /**
     * Method to return String(yyyy-MM-dd HH:mm:ss)
     * @param localDateTime
     */
    public static String timeFormatter(LocalDateTime localDateTime){
        if (localDateTime == null) return null;

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public String getLastInitTime(){
        return timeFormatter(lastInitTime);
    }

    public String collectionType(){
        return collection.getClass().getName();
    }
    public int collectionSize(){
        return collection.size();
    }
    public void clear(){
        this.collection.clear();
        lastInitTime=LocalDateTime.now();
    }
    public City getById(int id){
        for (City element:collection){
            if(element.getId()==id) return element;
        }
        return null;
    }
    public void removeElement(City city){
        collection.remove(city);
    }
    public void addElement(City city) throws InvalidFormException{
        if (!(city.validate()&& city.getCoordinates().validate() && city.getGovernor().validate())) {
            throw new InvalidFormException() ;

        }
        collection.add(city);
    }
    public void editById(int id, City newElement) throws InvalidFormException{
        City pastElement=this.getById(id);
        this.removeElement(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
        City.updateId(this.getCollection());

    }
    public void addElements(Collection<City> collection) throws InvalidFormException{
        if(collection==null) return;
        for (City city:collection){
            this.addElement(city);
        }
    }
    public void removeElements(Collection<City> collection){
        this.collection.removeAll(collection);
    }
    public boolean checkExist(int id){
        return collection.stream().anyMatch((x)->x.getId()==id);
    }


}
