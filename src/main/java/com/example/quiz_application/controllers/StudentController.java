package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.CompleteStudentRegistrationResponse;
import com.example.quiz_application.dtos.response.RegisterStudentResponse;
import com.example.quiz_application.dtos.response.StudentResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import com.example.quiz_application.exceptions.InvalidTokenException;
import com.example.quiz_application.exceptions.StudentAlreadyTakenException;
import com.example.quiz_application.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<RegisterStudentResponse> register(@RequestBody RegisterStudentRequest request) throws InvalidRegistrationDetails {
        return new ResponseEntity<>(studentService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/institute")
    public ResponseEntity<CompleteStudentRegistrationResponse> register(@RequestBody CompleteStudentRegistrationRequest request) throws InvalidTokenException, StudentAlreadyTakenException, InstituteDoesNotExistException, IOException {
        return new ResponseEntity<>(studentService.register(request), HttpStatus.OK);
    }

    @GetMapping("/institute/{instituteId}")
    public ResponseEntity<List<StudentResponse>> findAllTeacher(@PathVariable Long instituteId) throws InstituteDoesNotExistException {
        return new ResponseEntity<>(studentService.findAllStudentsBy(instituteId), HttpStatus.OK);
    }


}
