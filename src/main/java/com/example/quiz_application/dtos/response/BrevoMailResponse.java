package com.example.quiz_application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrevoMailResponse {
    private String event;
    private String message_id;
    private String date;
}
