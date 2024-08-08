package com.example.swiggato.exceptions;

public class MenuItemNotFound extends RuntimeException{
    public MenuItemNotFound(String message){
        super(message);
    }
}
