package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.RemoveInstituteFromTeacherRequest;
import com.example.quiz_application.dtos.request.UploadQuizRequest;
import com.example.quiz_application.dtos.response.ApiResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherInstituteController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> register(@RequestBody CompleteTeacherRegistration request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.completeRegistration(request)), HttpStatus.CREATED);
        }catch (InstituteDoesNotExistException | InvalidPasswordException |
                IOException | InvalidRegistrationDetails | InvalidTokenException  exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/teacher")
    public ResponseEntity<ApiResponse<?>> addTeacher(@RequestBody AddTeacherToSchoolRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.addTeacherToSchool(request)), HttpStatus.ACCEPTED);
        }catch (InstituteDoesNotExistException | TeacherDoesNotExistException | InstitutionAlreadyExist exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> findTeacher(@RequestParam("mail") String email) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.findTeacher(email)), HttpStatus.FOUND);
        }catch (TeacherDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/institute")
    public ResponseEntity<ApiResponse<?>> removeInstitute(@RequestBody RemoveInstituteFromTeacherRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.removeInstitute(request)), HttpStatus.OK);
        }catch (InstitutionDoesNotBelongToTeacherException | InstituteDoesNotExistException | TeacherDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/institutes")
    public ResponseEntity<ApiResponse<?>> teacherInstitute(@RequestParam("mail") String teacher_email){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.getInstitute(teacher_email)), HttpStatus.FOUND);
        }catch (TeacherDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/quiz")
    public ResponseEntity<ApiResponse<?>> findQuiz(@RequestParam("mail") String teacher_email){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.getTeacherQuiz(teacher_email)), HttpStatus.FOUND);
        }catch (TeacherDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<ApiResponse<?>> createQuiz(@RequestBody UploadQuizRequest request, @RequestParam("file") MultipartFile file){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.uploadQuiz(request, file)), HttpStatus.OK);
        }catch (FileFormatException | TeacherDoesNotExistException | IOException exception ){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<ApiResponse<?>> findTeachersByInstitute(@RequestParam("instituteId") Long instituteId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, teacherService.findTeachersBy(instituteId)), HttpStatus.OK);
        }catch (InstituteDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }
}
