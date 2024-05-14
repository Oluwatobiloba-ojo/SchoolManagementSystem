package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Quiz_Question;
import com.example.quiz_application.exceptions.FileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IExcelService {
    void validate(MultipartFile file) throws FileFormatException;
    List<Quiz_Question> convertFileToQuestion(InputStream inputStream, Quiz quiz);
}
