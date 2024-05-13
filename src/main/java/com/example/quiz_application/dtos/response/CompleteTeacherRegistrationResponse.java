package com.example.quiz_application.dtos.response;

import com.example.quiz_application.data.model.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteTeacherRegistrationResponse {
    private String message;
    private Teacher teacher;
}
