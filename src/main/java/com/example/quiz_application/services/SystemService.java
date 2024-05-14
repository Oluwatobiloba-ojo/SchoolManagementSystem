package com.example.quiz_application.services;

import jakarta.servlet.http.HttpServletResponse;

public interface SystemService {

    void downloadTeacherFile(HttpServletResponse response);
}
