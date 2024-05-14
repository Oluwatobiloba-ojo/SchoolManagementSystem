package com.example.quiz_application.data.repository;

import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByWriter(Teacher teacher);
}
