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
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import com.example.quiz_application.exceptions.InvalidTokenException;
import com.example.quiz_application.exceptions.StudentAlreadyTakenException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.quiz_application.util.AppUtils.STUDENT_ALREADY_TAKEN;
import static com.example.quiz_application.util.AppUtils.STUDENT_INVALID_EMAIL;
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

    @Override
    public RegisterStudentResponse register(RegisterStudentRequest request) throws InvalidRegistrationDetails {
        if(!verifyEmail(request.getEmail())) throw new InvalidRegistrationDetails(STUDENT_INVALID_EMAIL);
        Student student = mapper.map(request, Student.class);
        Student savedStudent = repository.save(student);
        System.out.println(savedStudent);
        RegisterStudentResponse response = new RegisterStudentResponse();
        response.setMessage("Student registered successfully");
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
        response.setMessage("Student Already Added to institution");
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
