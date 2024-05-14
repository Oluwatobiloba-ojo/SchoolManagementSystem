package com.example.quiz_application.data.repository;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Quiz_Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Quiz_Question, Long> {
    List<Quiz_Question> findAllQuestionByQuiz(Quiz quiz);

}
