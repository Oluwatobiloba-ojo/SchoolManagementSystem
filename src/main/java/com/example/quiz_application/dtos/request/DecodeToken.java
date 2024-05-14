package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DecodeToken {
    private String email;
    private String iss;
    private String sub;
    private String exp;
    private Long instituteId;
}
