package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.CreateTokenRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.dtos.response.CompleteStudentRegistrationResponse;
import com.example.quiz_application.dtos.response.RegisterStudentResponse;
import com.example.quiz_application.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtService jwtService;
    @Test
    public void testThatStudentCanCreateAStudentAccountWithTheirEmailAndOtherValidateTheirEmail() {
        RegisterStudentRequest request = new RegisterStudentRequest();
        request.setName("ojo");
        request.setEmail("ooluwatobi@gmail");
        request.setPassword("password1234");
        request.setConfirmPassword("password1234");
        assertThrows(InvalidRegistrationDetails.class, () -> studentService.register(request));
    }
    @Test
    public void testThatStudentCanCreateAStudentAccountWithTheirEmail() throws InvalidRegistrationDetails, InvalidPasswordException {
        RegisterStudentRequest request = new RegisterStudentRequest();
        request.setName("ojo");
        request.setEmail("ooluwatobi@gmail.com");
        request.setPassword("password1234");
        request.setConfirmPassword("password1234");
        long number_of_student = studentService.findAllStudents().size();
        RegisterStudentResponse response = studentService.register(request);
        assertNotNull(response);
        assertThat(number_of_student+1).isEqualTo(studentService.findAllStudents().size());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatStudentCreateAnAccountWhichAlreadyHaveAnInstitution() throws InvalidTokenException, StudentAlreadyTakenException, InstituteDoesNotExistException, IOException {
        CreateTokenRequest request = new CreateTokenRequest();
        request.setEmail("ooluwatobi895@gmail.com");
        request.setInstituteId(200L);
        String token = jwtService.createToken(request);
        CompleteStudentRegistrationRequest request1 = new CompleteStudentRegistrationRequest();
        request1.setToken(token);
        request1.setName("oluwatobi");
        request1.setPassword("password1234");
        CompleteStudentRegistrationResponse response = studentService.register(request1);
        assertNotNull(response);
    }
    @Test
    @Sql("/scripts/insert.sql")
    public void testThatStudentWhichAlreadyHaveAnAccountWithAnInstitutionCanNotUseThatSameAccountForAnotherInstitution() {
        CreateTokenRequest request = new CreateTokenRequest();
        request.setEmail("ooluwatobi885@gmail.com");
        request.setInstituteId(200L);
        String token = jwtService.createToken(request);
        CompleteStudentRegistrationRequest request1 = new CompleteStudentRegistrationRequest();
        request1.setToken(token);
        request1.setName("oluwatobi");
        request1.setPassword("password1234");
        assertThrows(StudentAlreadyTakenException.class, ()-> studentService.register(request1));
    }


}