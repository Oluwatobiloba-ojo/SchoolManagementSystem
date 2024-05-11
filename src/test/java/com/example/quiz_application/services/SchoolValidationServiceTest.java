package com.example.quiz_application.services;

import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SchoolValidationServiceTest {

    @Autowired
    private SchoolValidationService validationService;

    @Test
    public void testThatValidateASchoolRegistrationNumber() throws InvalidRegistrationDetails {
        SchoolValidationResponse response = validationService.validateRC("2345689");
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatRegistrationNumberDoesThatWhichDoesNotExist(){
        assertThrows(InvalidRegistrationDetails.class, ()-> validationService.validateRC("12345"));
    }
    @Test
    public void testThatRegistrationNumberDoesThatWhichDoesNotExist2(){
        assertThrows(InvalidRegistrationDetails.class, ()-> validationService.validateRC("i am a boy"));
    }
}