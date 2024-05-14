package com.example.quiz_application.exceptions;

public class StudentAlreadyTakenException extends Exception {
    public StudentAlreadyTakenException(String message) {
        super(message);
    }
}
