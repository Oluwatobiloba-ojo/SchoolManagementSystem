package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherInvitationRequestMessage {
    private String teacherEmail;
    private String instituteName;
    private String instituteAddress;
    private String Link;
}
