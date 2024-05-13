package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.TeacherCreateTokenRequest;
import com.example.quiz_application.dtos.request.TeacherDecodeToken;
import com.example.quiz_application.exceptions.InvalidTokenException;

import java.io.IOException;

public interface JwtService {
    String createToken(TeacherCreateTokenRequest request);
    TeacherDecodeToken decode(String token) throws InvalidTokenException, IOException;
    void verifyJwtToken(String token) throws InvalidTokenException;
}
