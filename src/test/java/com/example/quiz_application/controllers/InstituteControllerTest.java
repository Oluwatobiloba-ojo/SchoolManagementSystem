package com.example.quiz_application.controllers;

import com.example.quiz_application.dtos.request.AddStudentRequest;
import com.example.quiz_application.dtos.request.AddTeacherRequest;
import com.example.quiz_application.dtos.request.InstitutionRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InstituteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testThatInstituteCanRegisterWithValidRcNumber() throws Exception {
        InstitutionRegistrationRequest request = new InstitutionRegistrationRequest();
        request.setRegistrationNumber("2345689");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/institution")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatInstituteCanSendRequestOfJoiningToTheTeacher() throws Exception {
        AddTeacherRequest request = new AddTeacherRequest();
        request.setId(200L);
        request.setTeacher_emails(List.of("ojot630@gmail.com", "ooluwatobi825@gmail.com"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/institution/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    @Sql("/scripts/insert.sql")
    public void testThatInstituteCanSendRequestOfJoiningToTheStudent() throws Exception {
        AddStudentRequest request = new AddStudentRequest();
        request.setInstitutionId(200L);
        request.setStudentEmails(List.of("ojot630@gmail.com"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/institution/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatFindAnInstitute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/institution/"+200L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testFindAllInstitute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/institution")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }


}