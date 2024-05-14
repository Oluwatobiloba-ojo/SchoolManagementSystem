package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.*;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.dtos.response.RemoveInstituteFromTeacherResponse;
import com.example.quiz_application.exceptions.*;
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
    public void testThatTeacherWhenSeeEmailCanInitializeTheEndpointByAddingSomeDetails() throws InstituteDoesNotExistException, InvalidPasswordException, InvalidTokenException, IOException {
        CreateTokenRequest request = new CreateTokenRequest();
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

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatTeachersCanRemoveInstitutionFromTheirListOfInstitution() throws TeacherDoesNotExistException, InstitutionDoesNotBelongToTeacherException, InstituteDoesNotExistException, InstitutionAlreadyExist {
        RemoveInstituteFromTeacherRequest request = new RemoveInstituteFromTeacherRequest();
        request.setEmail("ojot630@gmail.com");
        request.setInstituteId(200L);
        long numbers_of_institute = teacherService.findTeacher(request.getEmail()).getInstitutions().size();
        RemoveInstituteFromTeacherResponse response = teacherService.removeInstitute(request);
        assertThat(response).isNotNull();
        assertThat(numbers_of_institute-1).isEqualTo(teacherService.findTeacher(request.getEmail()).getInstitutions().size());
    }
}

