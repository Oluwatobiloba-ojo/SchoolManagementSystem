package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.AddTeacherToSchoolRequest;
import com.example.quiz_application.dtos.request.CompleteTeacherRegistration;
import com.example.quiz_application.dtos.request.TeacherCreateTokenRequest;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InstitutionAlreadyExist;
import com.example.quiz_application.exceptions.InvalidPasswordException;
import com.example.quiz_application.exceptions.InvalidTokenException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtService jwtService;

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatTeacherWhenSeeNotificationCanInitializeTheEndpointByAddingSomeDetails() throws InstituteDoesNotExistException, InvalidPasswordException, InvalidTokenException, IOException {
        TeacherCreateTokenRequest request = new TeacherCreateTokenRequest();
        request.setEmail("opeoluwaagnes@gmail.com");
        request.setInstituteId(200L);
        String token = jwtService.createToken(request);
        CompleteTeacherRegistration completeTeacherRegistration = new CompleteTeacherRegistration();
        completeTeacherRegistration.setPassword("Olawale1234");
        completeTeacherRegistration.setConfirmPassword("Olawale1234");
        completeTeacherRegistration.setName("Olawale");
        completeTeacherRegistration.setToken(token);
        CompleteTeacherRegistrationResponse response = teacherService.completeRegistration(completeTeacherRegistration);
        assertThat(response).isNotNull();
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatTheTeacherCanNotBeAddedToASchoolAlreadyBelongAgain()  {
        AddTeacherToSchoolRequest request = new AddTeacherToSchoolRequest();
        request.setEmail("ojot630@gmail.com");
        request.setInstituteId(200L);
        assertThrows(InstitutionAlreadyExist.class, () -> teacherService.addTeacherToSchool(request));
    }

}