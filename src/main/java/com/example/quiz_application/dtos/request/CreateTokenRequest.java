package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTokenRequest {
    private String email;
    private Long instituteId;
}
