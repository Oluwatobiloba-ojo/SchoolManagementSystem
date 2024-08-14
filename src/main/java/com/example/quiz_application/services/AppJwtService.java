package com.example.quiz_application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.quiz_application.dtos.request.CreateTokenRequest;
import com.example.quiz_application.dtos.request.DecodeToken;
import com.example.quiz_application.exceptions.InvalidTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Base64.Decoder;

import static com.example.quiz_application.util.AppUtils.INVALID_TOKEN;

@Service
public class AppJwtService implements JwtService{

    private String secretKey = System.getenv("oauth.api.key");
    @Override
    public String createToken(CreateTokenRequest request) {
        return JWT.create()
                .withIssuer("quiz-application")
                .withSubject("access_token")
                .withClaim("email", request.getEmail())
                .withClaim("instituteId", request.getInstituteId())
                .withExpiresAt(Instant.now().plusSeconds(20*86400))
                .sign(Algorithm.HMAC256(secretKey));
    }

    @Override
    public DecodeToken decode(String token) throws InvalidTokenException, IOException {
        verifyJwtToken(token);
        ObjectMapper mapper = new ObjectMapper();
        Decoder decoder = Base64.getUrlDecoder();
        String[] tokens = token.split("\\.");
        String payload = new String(decoder.decode(tokens[1]));
        return mapper.readValue(payload.getBytes(), DecodeToken.class);
    }

    @Override
    public void verifyJwtToken(String token) throws InvalidTokenException {
        try {
            Algorithm signatureAlgorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(signatureAlgorithm)
                    .withIssuer("quiz-application")
                    .build();
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new InvalidTokenException(INVALID_TOKEN);
        }
    }
}