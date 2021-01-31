package com.hse.organizer.service;

import com.hse.organizer.model.Doctor;
import com.hse.organizer.model.User;

import java.util.List;

/**
 * Service interface for class {@link Doctor}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

public interface DoctorService {
    Doctor register(Doctor doctor);

    List<Doctor> getAll();

    Doctor findById(Long id);

    Doctor findByName(String name);

    void delete(Long id);
}
