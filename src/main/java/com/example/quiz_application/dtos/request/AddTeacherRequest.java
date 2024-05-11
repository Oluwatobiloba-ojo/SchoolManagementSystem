package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddTeacherRequest {
    private Long id;
    private List<String> teacher_emails;
}
