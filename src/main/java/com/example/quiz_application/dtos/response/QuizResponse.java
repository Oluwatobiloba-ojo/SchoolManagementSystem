package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizResponse {
    private String title;
    private List<QuestionResponse> questions;

    public QuizResponse(String title, List<QuestionResponse> questions){
        this.title = title;
        this.questions = questions;
    }

}
