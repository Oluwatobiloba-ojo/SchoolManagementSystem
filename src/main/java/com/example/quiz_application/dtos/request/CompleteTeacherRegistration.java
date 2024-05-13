package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompleteTeacherRegistration {
    private String password;
    private String confirmPassword;
    private String name;
    private String token;
}
