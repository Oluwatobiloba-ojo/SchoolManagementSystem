package com.example.quiz_application.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class Quiz_Question {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String answer;
    @ManyToOne(cascade = {MERGE, PERSIST})
    private Quiz quiz;
}
