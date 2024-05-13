package com.example.quiz_application.exceptions;

public class InstitutionDoesNotBelongToTeacherException extends Exception {
    public InstitutionDoesNotBelongToTeacherException(String message) {
        super(message);
    }
}
