package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadQuizRequest {
    private String email;
    private String title;
    private String description;
}
