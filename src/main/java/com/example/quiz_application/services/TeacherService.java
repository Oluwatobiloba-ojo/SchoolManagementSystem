package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.RemoveInstituteFromTeacherRequest;
import com.example.quiz_application.dtos.request.UploadQuizRequest;
import com.example.quiz_application.dtos.response.*;
import com.example.quiz_application.exceptions.*;

import java.io.IOException;
import java.util.List;

public interface TeacherService {
    CompleteTeacherRegistrationResponse completeRegistration(CompleteTeacherRegistration completeTeacherRegistration) throws InvalidPasswordException, InstituteDoesNotExistException, InvalidTokenException, IOException;
    Teacher findTeacher(String email) throws TeacherDoesNotExistException;
    AddTeacherToSchoolResponse addTeacherToSchool(AddTeacherToSchoolRequest request) throws InstituteDoesNotExistException, TeacherDoesNotExistException, InstitutionAlreadyExist;
    RemoveInstituteFromTeacherResponse removeInstitute(RemoveInstituteFromTeacherRequest request) throws TeacherDoesNotExistException, InstituteDoesNotExistException, InstitutionAlreadyExist, InstitutionDoesNotBelongToTeacherException;
    UploadQuizResponse uploadQuiz(UploadQuizRequest request) throws FileFormatException, TeacherDoesNotExistException, IOException;
    List<QuizResponse> getTeacherQuiz(String email) throws TeacherDoesNotExistException;

//    List<TeacherResponse> findTeachersBy(Long instituteId);
}
