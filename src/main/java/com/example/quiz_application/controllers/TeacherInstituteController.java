package com.example.quiz_application.controllers;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.RemoveInstituteFromTeacherRequest;
import com.example.quiz_application.dtos.response.AddTeacherToSchoolResponse;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.dtos.response.QuizResponse;
import com.example.quiz_application.dtos.response.RemoveInstituteFromTeacherResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherInstituteController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<CompleteTeacherRegistrationResponse> register(@RequestBody CompleteTeacherRegistration request) throws InvalidTokenException,
            InstituteDoesNotExistException, InvalidPasswordException, IOException {
        return new ResponseEntity<>(teacherService.completeRegistration(request), HttpStatus.CREATED);
    }

    @PostMapping("/teacher")
    public ResponseEntity<AddTeacherToSchoolResponse> addTeacher(
            @RequestBody AddTeacherToSchoolRequest request) throws InstituteDoesNotExistException, TeacherDoesNotExistException, InstitutionAlreadyExist {
        return new ResponseEntity<>(teacherService.addTeacherToSchool(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable String email) throws TeacherDoesNotExistException {
        return new ResponseEntity<>(teacherService.findTeacher(email), HttpStatus.FOUND);
    }

    @DeleteMapping("/institute")
    public ResponseEntity<RemoveInstituteFromTeacherResponse> removeInstitute(
            @RequestBody RemoveInstituteFromTeacherRequest request) throws InstitutionDoesNotBelongToTeacherException, InstituteDoesNotExistException, TeacherDoesNotExistException, InstitutionAlreadyExist {
        return new ResponseEntity<>(teacherService.removeInstitute(request), HttpStatus.OK);
    }

    @GetMapping("/intitutes/{teacher_email}")
    public ResponseEntity<List<Institution>> teacherInstitute(@PathVariable String teacher_email) throws TeacherDoesNotExistException {
        return new ResponseEntity<>(teacherService.getInstitute(teacher_email), HttpStatus.FOUND);
    }

    @GetMapping("/quiz/{teacher_email}")
    public ResponseEntity<List<QuizResponse>> findQuiz(@PathVariable String teacher_email) throws TeacherDoesNotExistException {
        return new ResponseEntity<>(teacherService.getTeacherQuiz(teacher_email), HttpStatus.FOUND);
    }
}
