package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionRegistrationRequest {
    private String registrationNumber;
    private String name;
    private String email;
    private String location;
    private String description;

}
