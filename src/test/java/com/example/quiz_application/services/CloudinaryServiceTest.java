package com.example.quiz_application.services;

import com.example.quiz_application.dtos.request.UploadFileRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class CloudinaryServiceTest {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Test
    public void testThatFilesCanBeUploadedToACloudAndChecksIfItIsThere() throws IOException {
        UploadFileRequest request = new UploadFileRequest();
        request.setFilePath("C:\\Users\\User\\Pictures\\1577682914890.png");
        String fileUrl = cloudinaryService.uploadFile(request);
        assertNotNull(fileUrl);
        byte[] file = cloudinaryService.retrieveFile(fileUrl);
        assertNotNull(file);
    }

}