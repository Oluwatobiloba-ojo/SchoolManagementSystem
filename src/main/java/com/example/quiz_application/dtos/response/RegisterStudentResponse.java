package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentResponse {
    private String message;
    private StudentResponse studentResponse;

}
