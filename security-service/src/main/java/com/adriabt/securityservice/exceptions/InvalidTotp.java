package com.adriabt.securityservice.exceptions;

public class InvalidTotp extends Exception{
    public InvalidTotp(String message){
        super(message);
    }
}
