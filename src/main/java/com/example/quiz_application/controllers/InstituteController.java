package com.example.quiz_application.controllers;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.dtos.response.AddStudentResponse;
import com.example.quiz_application.dtos.response.AddTeacherResponse;
import com.example.quiz_application.dtos.response.InstituteResponse;
import com.example.quiz_application.dtos.response.RegisterResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import com.example.quiz_application.services.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institution")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping()
    public ResponseEntity<RegisterResponse> register(@RequestBody InstitutionRegistrationRequest request) throws InvalidRegistrationDetails {
        return new ResponseEntity<>(instituteService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/teacher")
    public ResponseEntity<AddTeacherResponse> inviteTeacher(@RequestBody AddTeacherRequest request) throws InstituteDoesNotExistException {
        return new ResponseEntity<>(instituteService.addTeachers(request), HttpStatus.ACCEPTED);
    }

    @PostMapping("/student")
    public ResponseEntity<AddStudentResponse> inviteStudent(@RequestBody AddStudentRequest request) throws InstituteDoesNotExistException {
        return new ResponseEntity<>(instituteService.addStudents(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institution> findInstitute(@PathVariable Long id) throws InstituteDoesNotExistException {
        return new ResponseEntity<>(instituteService.findInstitute(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<InstituteResponse>> findAllInstitute(){
        return new ResponseEntity<>(instituteService.findAllInstitute(), HttpStatus.FOUND);
    }



}
