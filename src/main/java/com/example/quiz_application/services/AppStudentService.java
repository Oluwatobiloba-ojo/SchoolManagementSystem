package com.example.quiz_application.services;

import com.example.quiz_application.data.model.Institution;
import com.example.quiz_application.data.model.Student;
import com.example.quiz_application.data.repository.StudentRepository;
import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.DecodeToken;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.CompleteStudentRegistrationResponse;
import com.example.quiz_application.dtos.response.RegisterStudentResponse;
import com.example.quiz_application.dtos.response.StudentResponse;
import com.example.quiz_application.exceptions.*;
import com.example.quiz_application.util.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.quiz_application.util.AppUtils.*;
import static com.example.quiz_application.util.Validation.verifyEmail;

@Service
public class AppStudentService implements StudentService{
    @Autowired
    private StudentRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private SchoolValidationService schoolValidationService;

    @Override
    public RegisterStudentResponse register(RegisterStudentRequest request) throws InvalidRegistrationDetails, InvalidPasswordException {
        schoolValidationService.validatePassword(request.getPassword(), request.getConfirmPassword());
        if(!verifyEmail(request.getEmail())) throw new InvalidRegistrationDetails(STUDENT_INVALID_EMAIL);
        Student student = mapper.map(request, Student.class);
        Student savedStudent = repository.save(student);
        RegisterStudentResponse response = new RegisterStudentResponse();
        response.setMessage(STUDENT_REGISTRATION);
        response.setStudentResponse(mapper.map(savedStudent, StudentResponse.class));
        return response;
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        return repository.findAll().stream()
                .map(student -> mapper.map(student, StudentResponse.class)).toList();
    }

    @Override
    public CompleteStudentRegistrationResponse register(CompleteStudentRegistrationRequest request1) throws InvalidTokenException, IOException, InstituteDoesNotExistException, StudentAlreadyTakenException {
        DecodeToken decodeToken = jwtService.decode(request1.getToken());
        Institution institution = instituteService.findInstitute(decodeToken.getInstituteId());
        Student student = repository.findByEmail(decodeToken.getEmail()).orElse(new Student());
        if (student.getId() == null){
            student = mapper.map(request1, Student.class);
            student.setEmail(decodeToken.getEmail());
            student.setInstitution(institution);
        }else {
            if (student.getInstitution() != null) throw new StudentAlreadyTakenException(STUDENT_ALREADY_TAKEN);
            student.setInstitution(institution);
        }
        Student savedStudent = repository.save(student);
        CompleteStudentRegistrationResponse response = new CompleteStudentRegistrationResponse();
        response.setMessage(AppUtils.STUDENT_REGISTRATION_COMPLETED);
        response.setStudent(mapper.map(savedStudent, StudentResponse.class));
        return response;
    }

    @Override
    public List<StudentResponse> findAllStudentsBy(Long instituteId) throws InstituteDoesNotExistException {
        Institution institution = instituteService.findInstitute(instituteId);
        return repository.findStudentByInstitution(institution)
                .stream()
                .map(student -> mapper.map(student, StudentResponse.class))
                .toList();
    }
}
