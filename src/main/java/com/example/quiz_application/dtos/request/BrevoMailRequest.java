package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BrevoMailRequest {
    private Sender sender;
    private List<Receipient> to;
    private String subject;
    private String htmlContent;
}
