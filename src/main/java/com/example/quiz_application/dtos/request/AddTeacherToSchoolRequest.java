package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTeacherToSchoolRequest {
    private Long instituteId;
    private String email;
}
