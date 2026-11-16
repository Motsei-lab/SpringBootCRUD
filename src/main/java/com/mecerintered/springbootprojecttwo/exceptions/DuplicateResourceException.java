package com.mecerintered.springbootprojecttwo.exceptions;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message){
        super(message);
    }
}
