package com.example.quiz_application.data.repository;

import com.example.quiz_application.data.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
