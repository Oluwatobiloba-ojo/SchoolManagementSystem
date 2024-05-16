package com.example.quiz_application.dtos.response;

import com.example.quiz_application.data.model.Institution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituteResponse {
    private String name;
    private String email;
    private String status;
    private String address;
    private Long id;

    public InstituteResponse(Institution institution) {
        name = institution.getName();
        email = institution.getEmail();
        address = institution.getLocation();
        id = institution.getId();
        status = institution.getStatus();
    }
}
