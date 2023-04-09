package main.java;

import main.java.collection.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Main {
    public static void main(String args[]){
        TreeSet<City> list=new TreeSet<>();
        City city1=new City(1,"nwa");
        City city2=new City(2,"njqw");
        City city3=new City(45,"iewq");
        City city211=new City(-2,"mas");
        City city21312=new City(100000,"jnass");
        list.add(city1);
        list.add(city2);
        list.add(city3);
        list.add(city211);
        list.add(city21312);

        test(list);




    }
    private static void test(TreeSet<? extends City> list){
        for (City city: list){
            System.out.println(city);
        }

    }
}
