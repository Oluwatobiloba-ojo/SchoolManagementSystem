package com.example.quiz_application.services;

import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;

public interface SchoolValidationService {
    SchoolValidationResponse validateRC(String number) throws InvalidRegistrationDetails;
}
