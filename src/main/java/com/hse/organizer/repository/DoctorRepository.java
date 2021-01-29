package com.hse.organizer.repository;

import com.hse.organizer.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getById(Long id);

    Doctor findByName(String name);
}
