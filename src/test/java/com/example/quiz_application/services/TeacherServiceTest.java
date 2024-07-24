package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.*;
import com.example.quiz_application.dtos.response.CompleteTeacherRegistrationResponse;
import com.example.quiz_application.dtos.response.RemoveInstituteFromTeacherResponse;
import com.example.quiz_application.dtos.response.UploadQuizResponse;
import com.example.quiz_application.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        request.setInstituteId(202L);
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
        System.out.println(teacherService.getInstitute(request.getEmail()));
        assertThat(response).isNotNull();
        assertThat(numbers_of_institute-1).isEqualTo(teacherService.findTeacher(request.getEmail()).getInstitutions().size());
    }
//
//    @Test
//    @Sql("/scripts/insert.sql")
//    public void testThatTeacherCanNotUploadAFileWhichIsNotExcelThrowsException() throws IOException {
//        UploadQuizRequest request = new UploadQuizRequest();
//        File file = new File("C:\\Users\\User\\Documents\\CHURCH FILES\\FORM.docx");
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("form", new FileInputStream(file));
//        request.setTitle("Wrong quiz");
//        request.setEmail("ojot630@gmail.com");
//        request.setDescription("It is a docx file oooo");
//        assertThrows(FileFormatException.class, () -> teacherService.uploadQuiz(request, mockMultipartFile));
//    }
//    @Test
//    @Sql("/scripts/insert.sql")
//    public void testThatTeacherCanUploadAFileWhichExcelWontThrowException() throws IOException, FileFormatException, TeacherDoesNotExistException {
//        UploadQuizRequest request = new UploadQuizRequest();
//        File file = new File("C:\\Users\\User\\Downloads\\rapid (1).xls");
//        MockMultipartFile file1 = new MockMultipartFile("form", null, Files.probeContentType(Path.of(file.getPath())), new FileInputStream(file));
//        request.setTitle("Wrong quiz");
//        request.setEmail("ojot630@gmail.com");
//        request.setDescription("It is a docx file oooo");
//        int numbers_of_quiz = teacherService.getTeacherQuiz(request.getEmail()).size();
//        UploadQuizResponse response = teacherService.uploadQuiz(request, file1);
//        assertThat(numbers_of_quiz+1).isEqualTo(teacherService.getTeacherQuiz(request.getEmail()).size());
//        assertThat(response).isNotNull();
//    }
//    @Test
//    @Sql("/scripts/insert.sql")
//    public void testThatTeacherCanUploadAnotherFileWhichExcelWontThrowException() throws IOException, FileFormatException, TeacherDoesNotExistException {
//        UploadQuizRequest request = new UploadQuizRequest();
//        File file = new File("C:\\Users\\User\\Downloads\\rapid.xls");
//        MockMultipartFile file1 = new MockMultipartFile("form", null, Files.probeContentType(Path.of(file.getPath())), new FileInputStream(file));
//        request.setTitle("Test Quiz");
//        request.setEmail("ojot630@gmail.com");
//        request.setDescription("It is a quiz for today");
//        int numbers_of_quiz = teacherService.getTeacherQuiz(request.getEmail()).size();
//        UploadQuizResponse response = teacherService.uploadQuiz(request, file1);
//        assertThat(numbers_of_quiz+1).isEqualTo(teacherService.getTeacherQuiz(request.getEmail()).size());
//        assertThat(response).isNotNull();
//    }

    @Test
    @Sql("/scripts/insert.sql")
    public void testThatTeacherCanUpdateQuestionOfAQuiz(){
        UpdateQuestionRequest request = new UpdateQuestionRequest();
        request.setQuestionId(3L);
        request.setQuestion("What is the longest mountain");
        request.setOptionA("everest");
        request.setOptionB("kilimongaro");
        request.setOptionC("Makalu");
        request.setOptionD("Cho Oyu");
    }


}

