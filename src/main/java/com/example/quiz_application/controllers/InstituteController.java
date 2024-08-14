package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.dtos.response.ApiResponse;
import com.example.quiz_application.dtos.response.InstituteResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidPasswordException;
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
    public ResponseEntity<ApiResponse<?>> register(@RequestBody InstitutionRegistrationRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, instituteService.register(request)), HttpStatus.CREATED);
        }catch (InvalidRegistrationDetails | InvalidPasswordException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/teacher")
    public ResponseEntity<ApiResponse<?>> inviteTeacher(@RequestBody AddTeacherRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, instituteService.addTeachers(request)), HttpStatus.ACCEPTED);
        }catch (InstituteDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/student")
    public ResponseEntity<ApiResponse<?>> inviteStudent(@RequestBody AddStudentRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true,instituteService.addStudents(request)), HttpStatus.ACCEPTED);
        }catch (InstituteDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> findInstitute(@RequestParam("id") Long id){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, instituteService.findInstitute(id)), HttpStatus.FOUND);
        }catch (InstituteDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<InstituteResponse>> findAllInstitute(){
        return new ResponseEntity<>(instituteService.findAllInstitute(), HttpStatus.FOUND);
    }



}
