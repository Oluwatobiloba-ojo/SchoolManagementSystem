package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.dtos.response.QuestionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
    List<QuestionResponse> findAllQuestionByQuiz(Quiz quiz);
    List<QuestionResponse> createQuestions(MultipartFile file, Quiz quiz) throws IOException;

}
