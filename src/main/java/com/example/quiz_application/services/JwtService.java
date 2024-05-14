package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.CreateTokenRequest;
import com.example.quiz_application.dtos.request.DecodeToken;
import com.example.quiz_application.exceptions.InvalidTokenException;

import java.io.IOException;

public interface JwtService {
    String createToken(CreateTokenRequest request);
    DecodeToken decode(String token) throws InvalidTokenException, IOException;
    void verifyJwtToken(String token) throws InvalidTokenException;
}
