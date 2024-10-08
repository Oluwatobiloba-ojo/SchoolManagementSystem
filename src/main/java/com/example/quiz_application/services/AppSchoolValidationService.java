package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.SchoolValidationRequest;
import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import com.example.quiz_application.exceptions.InvalidPasswordException;
import com.example.quiz_application.exceptions.InvalidRegistrationDetails;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.example.quiz_application.util.AppUtils.PASSWORD_EMPTY;
import static com.example.quiz_application.util.AppUtils.PASSWORD_NOT_MATCH;


@Service
public class AppSchoolValidationService implements SchoolValidationService{


    private static String apiUrl = System.getenv("API_URL");
    private String apiKey = System.getenv("API_KEY");

    public static void main(String[] args) {
        System.out.println(apiUrl);
        System.getenv().forEach((key, value) -> System.out.println(key + "_"+ value));
    }
    @Override
    public SchoolValidationResponse validateRC(String number) throws InvalidRegistrationDetails {
        System.getenv().forEach((key, value) -> System.out.println(key + "_"+ value));
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

    @Override
    public void validatePassword(String password, String confirmPassword) throws InvalidPasswordException {
        if (password.isEmpty()) throw new InvalidPasswordException(PASSWORD_EMPTY);
        if (!password.equals(confirmPassword)) throw new InvalidPasswordException(PASSWORD_NOT_MATCH);
    }
}
