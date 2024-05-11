package com.example.quiz_application.data.model;

import com.example.quiz_application.dtos.response.SchoolValidationResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String rc_number;
    private String location;
    private String status;

    public Institution(SchoolValidationResponse response) {
        this.name = response.getCompany_name();
        this.rc_number = response.getCompany_registration();
        this.status = response.getCompany_status();
        this.location = response.getCompany_address();
        this.email = response.getCompany_email();
    }
}
