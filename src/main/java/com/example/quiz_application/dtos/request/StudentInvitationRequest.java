package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInvitationRequest {
    private String studentEmail;
    private String instituteName;
    private String instituteAddress;
    private String token;
}
