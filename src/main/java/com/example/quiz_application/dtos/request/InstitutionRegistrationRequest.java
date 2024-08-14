package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionRegistrationRequest {
    private String registrationNumber;
    private String password;
    private String confirmPassword;
}
