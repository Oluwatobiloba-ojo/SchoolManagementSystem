package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.BrevoMailRequest;
import com.example.quiz_application.dtos.request.Receipient;
import com.example.quiz_application.dtos.request.Sender;
import com.example.quiz_application.dtos.request.TeacherInvitationRequestMessage;
import com.example.quiz_application.dtos.response.BrevoMailResponse;
import com.example.quiz_application.dtos.response.InstituteResponse;
import com.example.quiz_application.util.MessageDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class AppEmailService implements EmailService{

    @Value("${brevo.api.url}")
    private String url;
    @Value("${brevo.api.key}")
    private String apiKey;
    @Override
    public void sendBulkEmail(List<String> teacherEmails, InstituteResponse response) {
        for (String email: teacherEmails) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api-key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            BrevoMailRequest brevoMailRequest = new BrevoMailRequest();
            brevoMailRequest.setSender(new Sender(response.getName(), response.getEmail()));
            brevoMailRequest.setSubject("TEACHER-INVITATION-TO-QUIZ-APPLICATION");
            TeacherInvitationRequestMessage requestMessage = new TeacherInvitationRequestMessage();
            requestMessage.setTeacherEmail(email);
            requestMessage.setInstituteName(response.getName());
            requestMessage.setInstituteAddress(response.getAddress());
            brevoMailRequest.setHtmlContent(
                    MessageDetails.TEACHER_INVITATION_EMAIL(requestMessage));
            brevoMailRequest.setTo(List.of(new Receipient(email, email)));
            HttpEntity<BrevoMailRequest> entity = new HttpEntity<>(brevoMailRequest, headers);
            RestTemplate restTemplate = new RestTemplate();
           ResponseEntity<BrevoMailResponse> brevoResponse = restTemplate.postForEntity(url, entity, BrevoMailResponse.class);
            System.out.println(brevoResponse);
        }
    }
}
