package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteStudentRegistrationRequest {
    private String token;
    private String password;
    private String name;

}
