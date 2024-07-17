package com.example.quiz_application.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quiz_application.config.BeanConfig;
import com.example.quiz_application.dtos.request.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppCloudinaryService implements CloudinaryService{
    @Autowired
    private BeanConfig beanConfig;
    @Override
    public String uploadFile(UploadFileRequest request) throws IOException {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", beanConfig.cloud_name);
        config.put("api_key", beanConfig.api_key);
        config.put("api_secret", beanConfig.api_secret);
        Cloudinary cloudinary = new Cloudinary(config);
        Map uploadResult = cloudinary.uploader().upload(new File(request.getFilePath()), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }

    @Override
    public byte[] retrieveFile(String fileUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.getForEntity(fileUrl, byte[].class);

        return response.getBody();
    }
}
