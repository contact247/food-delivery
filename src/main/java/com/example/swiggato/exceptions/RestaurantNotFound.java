package com.example.swiggato.exceptions;

public class RestaurantNotFound extends RuntimeException{
    public RestaurantNotFound(String message){
        super(message);
    }
}
