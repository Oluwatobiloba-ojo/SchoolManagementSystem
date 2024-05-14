package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadQuizResponse {
    private String message;
    private QuizResponse quizResponse;

}
