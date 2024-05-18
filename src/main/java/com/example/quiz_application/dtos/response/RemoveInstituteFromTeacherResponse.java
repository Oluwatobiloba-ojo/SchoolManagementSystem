package com.example.quiz_application.dtos.response;

import com.example.quiz_application.data.model.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RemoveInstituteFromTeacherResponse {
    private String message;
    private Set<Institution> institutions;
}
