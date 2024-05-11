package com.example.quiz_application.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sender {
    private String name;
    private String email;

    public Sender(String name, String email) {
        this.email = email;
        this.name = name;
    }
}
