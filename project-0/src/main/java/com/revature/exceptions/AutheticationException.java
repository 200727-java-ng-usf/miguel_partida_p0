package com.revature.exceptions;

public class AutheticationException extends RuntimeException {

    public AutheticationException(){
        super("User authentication failed!");
    }

    public AutheticationException(String message){
        super(message);
    }
}
