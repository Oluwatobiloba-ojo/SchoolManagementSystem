package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.dtos.response.*;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidPasswordException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;

import java.util.List;

public interface InstituteService {
    RegisterResponse register(InstitutionRegistrationRequest request) throws InvalidRegistrationDetails, InvalidPasswordException;
    List<InstituteResponse> findAllInstitute();
    AddTeacherResponse addTeachers(AddTeacherRequest request) throws InstituteDoesNotExistException;
    AddStudentResponse addStudents(AddStudentRequest request) throws InstituteDoesNotExistException;
    Institution findInstitute(Long instituteId) throws InstituteDoesNotExistException;

}
