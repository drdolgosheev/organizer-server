package com.hse.organizer.repository;

import com.hse.organizer.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Diagnosis}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis getById(Long id);

    Diagnosis findByName(String name);
}
