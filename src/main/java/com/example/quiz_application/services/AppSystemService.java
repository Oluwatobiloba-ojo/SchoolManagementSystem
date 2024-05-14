package com.example.quiz_application.services;

import com.example.quiz_application.config.BeanConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class AppSystemService implements SystemService{
    @Autowired
    private BeanConfig beanConfig;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void downloadTeacherFile(HttpServletResponse response) {
        byte[] fileData = cloudinaryService.retrieveFile(beanConfig.file_url);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"rapid.xls\"");
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(fileData);
        }catch (IOException exception){
            System.err.println(exception.getMessage());
        }
    }
}
