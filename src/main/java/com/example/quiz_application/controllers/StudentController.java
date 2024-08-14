package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.ApiResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterStudentRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true,studentService.register(request)), HttpStatus.CREATED);
        }catch (InvalidRegistrationDetails | InvalidPasswordException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/institute")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody CompleteStudentRegistrationRequest request)  {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, studentService.register(request)), HttpStatus.OK);
        }catch (InvalidTokenException | StudentAlreadyTakenException | InstituteDoesNotExistException | IOException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/institute/{instituteId}")
    public ResponseEntity<ApiResponse<?>> findAllTeacher(@PathVariable Long instituteId){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, studentService.findAllStudentsBy(instituteId)), HttpStatus.OK);
        }catch (InstituteDoesNotExistException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


}
