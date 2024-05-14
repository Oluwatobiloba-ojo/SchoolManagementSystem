package com.example.quiz_application.util;

public class Validation {

    public static boolean verifyEmail(String email){
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
