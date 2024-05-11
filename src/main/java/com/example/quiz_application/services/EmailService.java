package com.example.quiz_application.services;

import com.example.quiz_application.dtos.response.InstituteResponse;

import java.util.List;

public interface EmailService {
    void sendBulkEmail(List<String> teacherEmails, InstituteResponse response);

}
