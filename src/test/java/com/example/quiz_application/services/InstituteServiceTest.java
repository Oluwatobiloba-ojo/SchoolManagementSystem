package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;
    InstitutionRegistrationRequest request;
    @BeforeEach
    public void setInstitute(){
        request = new InstitutionRegistrationRequest();
        request.setName("semicolon@africa");
        request.setLocation("314 Hebert Macaulay Way");
        request.setEmail("semicolon@gmail.com");
        request.setRegistrationNumber("");
        request.setDescription("A school where tech is been taught and learn");
    }


    @Test
    public void testThatInstituteGetToValidateTheirRegistrationNumberBeforeProgressingOnTheRegister(){
        request.setRegistrationNumber("I am a boy");
        assertThrows(InvalidRegistrationDetails.class, () -> instituteService.register(request));
    }

}