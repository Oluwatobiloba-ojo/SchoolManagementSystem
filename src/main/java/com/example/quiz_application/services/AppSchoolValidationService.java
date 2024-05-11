package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.SchoolValidationRequest;
import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class AppSchoolValidationService implements SchoolValidationService{

    @Value("${API_URL}")
    private String apiUrl;
    @Value("${API_KEY}")
    private String apiKey;
    @Override
    public SchoolValidationResponse validateRC(String number) throws InvalidRegistrationDetails {
        SchoolValidationRequest request = new SchoolValidationRequest();
        request.setSearch_term(number);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<SchoolValidationRequest> httpEntity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SchoolValidationResponse> response ;
        try {
            response = restTemplate.postForEntity(apiUrl, httpEntity, SchoolValidationResponse.class);
        }catch (HttpClientErrorException exception){
            throw new InvalidRegistrationDetails("Invalid Institution Registration Number");
        }
        return response.getBody();
    }
}
