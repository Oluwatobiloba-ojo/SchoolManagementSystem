package com.example.quiz_application.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    public String cloud_name = System.getenv("cloudinary.cloud.name");
    public String api_key = System.getenv("cloudinary.api.key");
    public String api_secret = System.getenv("cloudinary.api.secret");
    public String file_url = System.getenv("cloudinary.file.url");
    public String file_content_type = System.getenv("file.content.type");

    public static void main(String[] args) {
        System.getenv().forEach((key, value)-> System.out.println(key + "_" + value));
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
