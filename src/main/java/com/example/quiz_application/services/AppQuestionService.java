package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Quiz_Question;
import com.example.quiz_application.data.repository.QuestionRepository;
import com.example.quiz_application.dtos.response.QuestionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class AppQuestionService implements QuestionService{

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IExcelService excelService;

    @Override
    public List<QuestionResponse> findAllQuestionByQuiz(Quiz quiz) {
        return repository.findAllQuestionByQuiz(quiz).stream()
                .map(quizQuestion -> modelMapper.map(quizQuestion, QuestionResponse.class)).toList();
    }

    @Override
    public List<QuestionResponse> createQuestions(MultipartFile file, Quiz quiz) throws IOException {
        List<Quiz_Question> questions = excelService.convertFileToQuestion(file.getInputStream(), quiz);
        List<Quiz_Question> savedQuestions = repository.saveAll(questions);
        return savedQuestions.stream()
                .map(quizQuestion -> modelMapper.map(quizQuestion, QuestionResponse.class)).toList();
    }
}
