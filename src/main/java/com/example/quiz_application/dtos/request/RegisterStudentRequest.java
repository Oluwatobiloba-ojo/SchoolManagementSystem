package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentRequest {
    private String email;
    private String name;
    private String password;
    private String confirmPassword;
}
