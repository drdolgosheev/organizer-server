package com.hse.organizer.repository;

import com.hse.organizer.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis getById(Long id);

    Diagnosis findByName(String name);
}
