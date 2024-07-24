package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.dtos.response.AddStudentResponse;
import com.example.quiz_application.dtos.response.AddTeacherResponse;
import com.example.quiz_application.dtos.response.RegisterResponse;
import com.example.quiz_application.exceptions.InstituteDoesNotExistException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;
    InstitutionRegistrationRequest request;
    @BeforeEach
    public void setInstitute(){
        request = new InstitutionRegistrationRequest();
        request.setRegistrationNumber("2345689");
    }

    @Test
    public void testThatInstituteGetToValidateTheirRegistrationNumberBeforeProgressingOnTheRegister(){
        request.setRegistrationNumber("I am a boy");
        assertThrows(InvalidRegistrationDetails.class, () -> instituteService.register(request));
    }
    @Test
    public void testThatInstituteGetToValidateTheirRegistrationNumberThenMoveToTheRegister() throws InvalidRegistrationDetails {
        int formerSize = instituteService.findAllInstitute().size();
        RegisterResponse registerResponse = instituteService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(instituteService.findAllInstitute().size()).isEqualTo(formerSize+1);
    }
//    @Test
//    @Sql("/scripts/insert.sql")
//    public void testThatInstitutionCanAddTeacherToTheirInstitutionCreated() throws InstituteDoesNotExistException {
//        AddTeacherRequest request = new AddTeacherRequest();
//        request.setId(200L);
//        request.setTeacher_emails(List.of("chibuzor@gmail.com", "ojot630@gmail.com"));
//        AddTeacherResponse response = instituteService.addTeachers(request);
//        assertThat(response).isNotNull();
//    }
//    @Test
//    @Sql("/scripts/insert.sql")
//    public void testThatInstituteCanSendAnInviteToStudentOfTheirSchoolToJoin() throws InstituteDoesNotExistException {
//        AddStudentRequest request = new AddStudentRequest();
//        request.setInstitutionId(200L);
//        request.setStudentEmails(List.of("opeoluwaagnes@gmail.com", "deborahdelighted5@gmail.com", "ooluwatobi825@gmail.com"));
//        AddStudentResponse response = instituteService.addStudents(request);
//        assertNotNull(response);
//    }


}