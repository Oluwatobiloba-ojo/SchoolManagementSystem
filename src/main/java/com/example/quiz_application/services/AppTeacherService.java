package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.data.model.Teacher;
import com.example.quiz_application.data.repository.TeacherRepository;
import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.DecodeToken;
import com.example.quiz_application.dtos.request.RemoveInstituteFromTeacherRequest;
import com.example.quiz_application.dtos.response.AddTeacherToSchoolResponse;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.dtos.response.RemoveInstituteFromTeacherResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

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
        DecodeToken decodeToken = jwtService.decode(completeTeacherRegistration.getToken());
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
        verifyInstitution(teacher, institution);
        teacher.getInstitutions().add(institution);
        repository.save(teacher);
        AddTeacherToSchoolResponse response = new AddTeacherToSchoolResponse();
        response.setMessage(TEACHER_ADDED_TO_INSTITUTE);
        response.setTeacher(teacher);
        return response;
    }

    @Override
    public RemoveInstituteFromTeacherResponse removeInstitute(RemoveInstituteFromTeacherRequest request) throws TeacherDoesNotExistException, InstituteDoesNotExistException, InstitutionDoesNotBelongToTeacherException {
        Teacher teacher = findTeacher(request.getEmail());
        Institution institution = instituteService.findInstitute(request.getInstituteId());
        if(!checkIfInstituteExist(teacher, institution)) throw new InstitutionDoesNotBelongToTeacherException(INSTITUTE_REMOVE_MESSAGE);
        teacher.setInstitutions(teacher.getInstitutions().stream().filter(
                institution1 -> institution1.equals(institution)).collect(Collectors.toSet()));
        repository.save(teacher);
        RemoveInstituteFromTeacherResponse response = new RemoveInstituteFromTeacherResponse();
        response.setMessage(TEACHER_ADDED_TO_INSTITUTE);
        response.setData(institution);
        return response;
    }

    private static boolean checkIfInstituteExist(Teacher teacher, Institution institution) {
        return teacher.getInstitutions().stream().anyMatch(
                (institution1 -> institution1.getId().equals(institution.getId())));
    }

    private static void verifyInstitution(Teacher teacher, Institution institution) throws InstitutionAlreadyExist {
        if (checkIfInstituteExist(teacher, institution)) throw new InstitutionAlreadyExist(INSTITUTION_ALREADY_EXIST);
    }
}
