package com.anton.models.forms;


import com.anton.exeptions.InvalidFormException;


public abstract class AbstractForm<T> {
    public abstract T build() throws InvalidFormException;
}
