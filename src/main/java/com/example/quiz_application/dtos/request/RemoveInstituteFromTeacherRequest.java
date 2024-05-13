package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveInstituteFromTeacherRequest {
    private Long instituteId;
    private String email;
}
