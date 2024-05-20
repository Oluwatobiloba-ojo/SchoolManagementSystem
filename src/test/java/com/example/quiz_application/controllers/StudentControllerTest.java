package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.CompleteStudentRegistrationRequest;
import com.example.quiz_application.dtos.request.CreateTokenRequest;
import com.example.quiz_application.dtos.request.RegisterStudentRequest;
import com.example.quiz_application.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtService jwtService;


    @Test
    public void testThatStudentCanRegister() throws Exception{
        RegisterStudentRequest request = new RegisterStudentRequest();
        request.setName("Ola");
        request.setEmail("test@gmail.com");
        request.setPassword("Password123");
        request.setConfirmPassword("Password123");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testCompleteStudentRegistration() throws Exception{
        CreateTokenRequest createTokenRequest = new CreateTokenRequest();
        createTokenRequest.setEmail("ooluwatobi825@gmail.com");
        createTokenRequest.setInstituteId(200L);
        CompleteStudentRegistrationRequest request = new CompleteStudentRegistrationRequest();
        request.setPassword("Password123");
        request.setName("Olawale");
        request.setToken(jwtService.createToken(createTokenRequest));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student/institute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testGetAllStudentForAnInstitute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/institute/" + 200L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }



}
