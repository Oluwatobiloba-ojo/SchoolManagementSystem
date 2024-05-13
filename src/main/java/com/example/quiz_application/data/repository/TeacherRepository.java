package com.example.quiz_application.data.repository;

import com.example.quiz_application.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByEmail(String email);

}
