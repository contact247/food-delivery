package com.example.swiggato.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message){
        super(message);
    }
}
