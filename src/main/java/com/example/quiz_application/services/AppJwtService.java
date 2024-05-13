package com.example.quiz_application.services;

import com.auth0.jwt.JWT;
import com.example.quiz_application.dtos.request.TeacherCreateTokenRequest;
import com.example.quiz_application.dtos.request.TeacherDecodeToken;
import com.example.quiz_application.exceptions.InvalidTokenException;
import com.example.quiz_application.util.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Base64.Decoder;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Service
public class AppJwtService implements JwtService{
    @Value("${oauth.api.key}")
    private String secretKey;
    @Override
    public String createToken(TeacherCreateTokenRequest request) {
        return JWT.create()
                .withIssuer("quiz-application")
                .withSubject("access_token")
                .withClaim("email", request.getEmail())
                .withClaim("instituteId", request.getInstituteId())
                .withExpiresAt(Instant.now().plusSeconds(20*86400))
                .sign(HMAC256(secretKey));
    }

    @Override
    public TeacherDecodeToken decode(String token) throws InvalidTokenException, IOException {
        verifyJwtToken(token);
        ObjectMapper mapper = new ObjectMapper();
        Decoder decoder = Base64.getUrlDecoder();
        String[] tokens = token.split("\\.");
        String payload = new String(decoder.decode(tokens[1]));
        return mapper.readValue(payload.getBytes(), TeacherDecodeToken.class);
    }

    @Override
    public void verifyJwtToken(String token) throws InvalidTokenException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), signatureAlgorithm.getJcaName());
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKeySpec)
                .build();
        try {
            jwtParser.parse(token);
        }catch (Exception e){
            throw new InvalidTokenException(AppUtils.INVALID_TOKEN);
        }
    }
}
