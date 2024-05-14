package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
}
