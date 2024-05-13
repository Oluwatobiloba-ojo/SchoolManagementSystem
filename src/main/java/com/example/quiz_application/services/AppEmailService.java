package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.*;
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

import static com.example.quiz_application.util.AppUtils.*;

@Service
public class AppEmailService implements EmailService{

    @Value("${brevo.api.url}")
    private String url;
    @Value("${brevo.api.key}")
    private String apiKey;
    @Override
    public void sendBulkEmail(List<String> teacherEmails, InstituteResponse response, String receiverCategory) {
        for (String email: teacherEmails) {
            HttpEntity<BrevoMailRequest> entity = getEntity(response, email, receiverCategory);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<BrevoMailResponse> brevoResponse = restTemplate.postForEntity(url, entity, BrevoMailResponse.class);
            System.out.println(brevoResponse.getBody());
        }
    }

    private HttpEntity<BrevoMailRequest> getEntity(InstituteResponse response, String email, String receiverCategory) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        BrevoMailRequest brevoMailRequest = createBrevoTeacherRequest(response, email, receiverCategory);
        return new HttpEntity<>(brevoMailRequest, headers);
    }

    private static BrevoMailRequest createBrevoTeacherRequest(InstituteResponse response, String email, String receiverCategory) {
        BrevoMailRequest brevoMailRequest = new BrevoMailRequest();
        brevoMailRequest.setSender(new Sender(response.getName(), response.getEmail()));
        setBrevoMailRequest(response, email, receiverCategory, brevoMailRequest);
        return brevoMailRequest;
    }

    private static void setBrevoMailRequest(InstituteResponse response, String email, String receiverCategory, BrevoMailRequest brevoMailRequest) {
        if (receiverCategory.equals(TEACHER)) {
            brevoMailRequest.setSubject(TEACHER_INVITE_SUBJECT);
            TeacherInvitationRequestMessage requestMessage = getContent(response, email);
            brevoMailRequest.setHtmlContent(
                    MessageDetails.TEACHER_INVITATION_EMAIL(requestMessage));
            brevoMailRequest.setTo(List.of(new Receipient(email, email)));
        } else if (receiverCategory.equals(STUDENT)) {
            brevoMailRequest.setSubject(STUDENT_INVITE_SUBJECT);
            StudentInvitationRequest request = getStudentContent(response, email);
            brevoMailRequest.setHtmlContent(
                    MessageDetails.STUDENT_INVITATION_EMAIL(request)
            );
            brevoMailRequest.setTo(List.of(new Receipient(email, email)));
        }
    }

    private static StudentInvitationRequest getStudentContent(InstituteResponse response, String email) {
        StudentInvitationRequest request = new StudentInvitationRequest();
        request.setStudentEmail(email);
        request.setInstituteAddress(response.getAddress());
        request.setInstituteName(response.getName());
        request.setInstituteId(request.getInstituteId());
        return request;
    }


    private static TeacherInvitationRequestMessage getContent(InstituteResponse response, String email) {
        TeacherInvitationRequestMessage requestMessage = new TeacherInvitationRequestMessage();
        requestMessage.setTeacherEmail(email);
        requestMessage.setInstituteName(response.getName());
        requestMessage.setInstituteAddress(response.getAddress());
        return requestMessage;
    }

}
