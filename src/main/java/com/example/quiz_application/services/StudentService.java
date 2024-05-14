package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.CompleteStudentRegistrationResponse;
import com.example.quiz_application.dtos.response.RegisterStudentResponse;
import com.example.quiz_application.dtos.response.StudentResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import com.example.quiz_application.exceptions.InvalidTokenException;
import com.example.quiz_application.exceptions.StudentAlreadyTakenException;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    RegisterStudentResponse register(RegisterStudentRequest request) throws InvalidRegistrationDetails;
    List<StudentResponse> findAllStudents();
    CompleteStudentRegistrationResponse register(CompleteStudentRegistrationRequest request1) throws InvalidTokenException, IOException, InstituteDoesNotExistException, StudentAlreadyTakenException;

}
