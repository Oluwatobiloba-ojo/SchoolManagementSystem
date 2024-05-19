package com.example.quiz_application.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Value("${cloudinary.cloud.name}")
    public String cloud_name;
    @Value("${cloudinary.api.key}")
    public String api_key;
    @Value("${cloudinary.api.secret}")
    public String api_secret;
    @Value("${cloudinary.file.url}")
    public String file_url;
    @Value("${file.content.type}")
    public String file_content_type;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
