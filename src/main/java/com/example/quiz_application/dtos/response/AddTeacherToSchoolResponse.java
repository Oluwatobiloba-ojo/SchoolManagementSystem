package com.example.quiz_application.dtos.response;

import com.example.quiz_application.data.model.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTeacherToSchoolResponse {
    private String message;
    private Teacher teacher;
}
