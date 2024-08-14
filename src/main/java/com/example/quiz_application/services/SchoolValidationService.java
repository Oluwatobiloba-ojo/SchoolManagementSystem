package com.example.quiz_application.services;

import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import com.example.quiz_application.exceptions.InvalidPasswordException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;

public interface SchoolValidationService {
    SchoolValidationResponse validateRC(String number) throws InvalidRegistrationDetails;

    void validatePassword(String password, String confirmPassword) throws InvalidPasswordException;
}
