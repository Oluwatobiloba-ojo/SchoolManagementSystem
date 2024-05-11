package com.example.quiz_application.services;

import com.example.quiz_application.dtos.response.TeacherResponse;

import java.util.Collection;
import java.util.List;

public interface TeacherService {
    List<TeacherResponse> findTeachersBy(Long instituteId);
}
