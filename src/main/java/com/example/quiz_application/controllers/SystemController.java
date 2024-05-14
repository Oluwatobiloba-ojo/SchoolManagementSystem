package com.example.quiz_application.controllers;

import com.example.quiz_application.services.SystemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SystemController {


    @Autowired
    private SystemService systemService;

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response){
        systemService.downloadTeacherFile(response);
    }
}
