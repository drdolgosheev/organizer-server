package com.hse.organizer.repository;

import com.hse.organizer.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Doctor}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getById(Long id);

    Doctor findByName(String name);
}
