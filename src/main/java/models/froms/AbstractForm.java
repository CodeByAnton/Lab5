package main.java.models.froms;


import main.java.exeptions.InvalidFormException;

public abstract class AbstractForm<T> {
    public abstract T build() throws InvalidFormException;
}
