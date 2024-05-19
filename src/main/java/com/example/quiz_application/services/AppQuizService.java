package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.data.repository.QuizRepository;
import com.example.quiz_application.dtos.request.UploadQuizRequest;
import com.example.quiz_application.dtos.response.QuestionResponse;
import com.example.quiz_application.dtos.response.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppQuizService implements QuizService{
    @Autowired
    private QuizRepository repository;
    @Autowired
    private QuestionService questionService;
    @Override
    public List<QuizResponse> findAllQuizBelonging(Teacher teacher) {
        List<Quiz> quizzes = repository.findAllByWriter(teacher);
        return quizzes.stream()
                .map(quiz ->
                        new QuizResponse(quiz.getTitle(),
                                questionService.findAllQuestionByQuiz(quiz))).toList();
    }

    @Override
    public QuizResponse createQuiz(Teacher teacher, UploadQuizRequest request, MultipartFile file) throws IOException {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setWriter(teacher);
        quiz.setDescription(request.getDescription());
        List<QuestionResponse> questionResponses = questionService.createQuestions(file, quiz);
        Quiz savedQuiz = repository.save(quiz);
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setTitle(savedQuiz.getTitle());
        quizResponse.setQuestions(questionResponses);
        return quizResponse;
    }
}
