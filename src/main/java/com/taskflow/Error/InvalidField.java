package com.taskflow.Error;

public class InvalidField extends RuntimeException {
    public InvalidField(String message){
        super(message);
    }
}
