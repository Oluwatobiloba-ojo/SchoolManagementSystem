package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String email;
    private String name;
}
