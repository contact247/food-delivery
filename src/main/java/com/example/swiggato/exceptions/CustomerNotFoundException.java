package com.example.swiggato.exceptions;

public class CustomerNotFoundException extends  RuntimeException{
    public CustomerNotFoundException(String messsage){
        super(messsage);
    }
}
