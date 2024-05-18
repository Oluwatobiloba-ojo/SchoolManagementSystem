package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTokenRequest {
    private String email;
    private Long instituteId;

    public CreateTokenRequest(String email, Long instituteId) {
        this.email = email;
        this.instituteId = instituteId;
    }

}
