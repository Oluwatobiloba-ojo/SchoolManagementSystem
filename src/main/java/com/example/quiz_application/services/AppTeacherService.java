package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.data.repository.TeacherRepository;
import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.TeacherDecodeToken;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.quiz_application.util.AppUtils.*;

@Service
public class AppTeacherService implements TeacherService{
    @Autowired
    private TeacherRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private InstituteService instituteService;
    @Override
    public CompleteTeacherRegistrationResponse completeRegistration(CompleteTeacherRegistration completeTeacherRegistration) throws InvalidPasswordException, InstituteDoesNotExistException, InvalidTokenException, IOException {
        TeacherDecodeToken decodeToken = jwtService.decode(completeTeacherRegistration.getToken());
        Institution institution = instituteService.findInstitute(decodeToken.getInstituteId());
        if (!completeTeacherRegistration.getPassword().equals(completeTeacherRegistration.getConfirmPassword())) throw new InvalidPasswordException(PASSWORD_NOT_MATCH);
        Teacher teacher = new Teacher();
        teacher.setName(completeTeacherRegistration.getName());
        teacher.setPassword(completeTeacherRegistration.getConfirmPassword());
        teacher.setEmail(decodeToken.getEmail());
        teacher.getInstitutions().add(institution);
        Teacher savedTeacher = repository.save(teacher);
        CompleteTeacherRegistrationResponse response = new CompleteTeacherRegistrationResponse();
        response.setMessage(AppUtils.TEACHER_COMPLETE_REGISTRATION);
        response.setTeacher(savedTeacher);
        return response;
    }

    @Override
    public Teacher findTeacher(String email) throws TeacherDoesNotExistException {
        return repository.findTeacherByEmail(email).
                orElseThrow(() -> new TeacherDoesNotExistException(TEACHER_DOES_NOT_EXIST));
    }

    @Override
    public AddTeacherToSchoolResponse addTeacherToSchool(AddTeacherToSchoolRequest request) throws InstituteDoesNotExistException, TeacherDoesNotExistException, InstitutionAlreadyExist {
        Institution institution = instituteService.findInstitute(request.getInstituteId());
        Teacher teacher = findTeacher(request.getEmail());
        if (teacher.getInstitutions().contains(institution)) throw new InstitutionAlreadyExist(INSTITUTION_ALREADY_EXIST);
        teacher.getInstitutions().add(institution);
        repository.save(teacher);
        AddTeacherToSchoolResponse response = new AddTeacherToSchoolResponse();
        response.setMessage("Teacher already added to institute");
        response.setTeacher(teacher);
        return response;
    }
}
