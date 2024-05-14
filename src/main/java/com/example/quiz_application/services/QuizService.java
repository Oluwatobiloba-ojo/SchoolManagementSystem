package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.dtos.request.UploadQuizRequest;
import com.example.quiz_application.dtos.response.QuizResponse;

import java.io.IOException;
import java.util.List;

public interface QuizService {

    List<QuizResponse> findAllQuizBelonging(Teacher teacher);
    QuizResponse createQuiz(Teacher teacher, UploadQuizRequest request) throws IOException;
}
