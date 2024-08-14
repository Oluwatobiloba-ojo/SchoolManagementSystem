package com.example.quiz_application.dtos.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private T data;
}
