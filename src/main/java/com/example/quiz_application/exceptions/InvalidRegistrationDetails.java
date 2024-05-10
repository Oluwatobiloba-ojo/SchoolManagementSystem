package com.example.quiz_application.exceptions;

public class InvalidRegistrationDetails extends Exception{
    public InvalidRegistrationDetails(String message){
        super(message);
    }
}
