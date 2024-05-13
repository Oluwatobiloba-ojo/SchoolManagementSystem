package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddStudentRequest {
    private Long institutionId;
    private List<String> studentEmails;
}
