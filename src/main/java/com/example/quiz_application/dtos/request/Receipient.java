package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Receipient {
    private String name;
    private String email;

    public Receipient(String name, String email) {
        this.email = email;
        this.name = name;
    }
}
