package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherCreateTokenRequest {
    private String email;
    private Long instituteId;
}
