package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.CompleteStudentRegistrationResponse;
import com.example.quiz_application.dtos.response.RegisterStudentResponse;
import com.example.quiz_application.dtos.response.StudentResponse;
import com.example.quiz_application.exceptions.*;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    RegisterStudentResponse register(RegisterStudentRequest request) throws InvalidRegistrationDetails, InvalidPasswordException;
    List<StudentResponse> findAllStudents();
    CompleteStudentRegistrationResponse register(CompleteStudentRegistrationRequest request1) throws InvalidTokenException, IOException, InstituteDoesNotExistException, StudentAlreadyTakenException;
    List<StudentResponse> findAllStudentsBy(Long instituteId) throws InstituteDoesNotExistException;
}
