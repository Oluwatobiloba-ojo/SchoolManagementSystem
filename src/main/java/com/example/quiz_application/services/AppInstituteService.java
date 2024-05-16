package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.data.repository.InstitutionRepository;
import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.CreateTokenRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.dtos.response.*;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.quiz_application.util.AppUtils.*;


@Service
public class AppInstituteService implements InstituteService{
    @Autowired
    private SchoolValidationService validationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private InstitutionRepository repository;
    @Override
    public RegisterResponse register(InstitutionRegistrationRequest request) throws InvalidRegistrationDetails {
        SchoolValidationResponse response = validationService.validateRC(request.getRegistrationNumber());
        Institution institution = new Institution(response);
        Institution savedInstitution = repository.save(institution);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(savedInstitution.getId());
        registerResponse.setMessage(REGISTRATION_SUCCESSFULLY);
        return registerResponse;
    }

    @Override
    public List<InstituteResponse> findAllInstitute() {
       return repository.findAll().stream().map(InstituteResponse::new).toList();
    }

    @Override
    public AddTeacherResponse addTeachers(AddTeacherRequest request) throws InstituteDoesNotExistException {
        InstituteResponse response = new InstituteResponse(findInstitute(request.getId()));
        emailService.sendBulkEmail(request.getTeacher_emails(), response, TEACHER);
        AddTeacherResponse addTeacherResponse = new AddTeacherResponse();
        addTeacherResponse.setMessage(INVITE_TEACHER_RESPONSE);
        return addTeacherResponse;
    }

    @Override
    public AddStudentResponse addStudents(AddStudentRequest request) throws InstituteDoesNotExistException {
        InstituteResponse instituteResponse = new InstituteResponse(findInstitute(request.getInstitutionId()));
        emailService.sendBulkEmail(request.getStudentEmails(), instituteResponse, STUDENT);
        AddStudentResponse response = new AddStudentResponse();
        response.setMessage(INVITE_STUDENT_RESPONSE);
        return response;
    }
    @Override
    public Institution findInstitute(Long id) throws InstituteDoesNotExistException {
        return repository.findById(id).orElseThrow(() ->
                new InstituteDoesNotExistException(String.format(INVALID_INSTITUTE_MESSAGE, id)));
    }


}
