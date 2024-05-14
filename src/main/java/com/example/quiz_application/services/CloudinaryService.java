package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.UploadFileRequest;

import java.io.IOException;

public interface CloudinaryService {

    String uploadFile(UploadFileRequest request) throws IOException;
    byte[] retrieveFile(String fileUrl);
}
