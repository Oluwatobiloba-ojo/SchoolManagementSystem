package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionRequest {
    private Long questionId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String answer;
}
