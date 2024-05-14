package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteStudentRegistrationResponse {
    private String message;
    private StudentResponse student;
}
