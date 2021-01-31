package com.hse.organizer.service.implementation;

import com.hse.organizer.model.Doctor;
import com.hse.organizer.repository.DiagnosisRepository;
import com.hse.organizer.repository.DoctorRepository;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DoctorService} interface.
 * Wrapper for {@link DoctorRepository} + business logic.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Service
@Slf4j
public class DoctorServiceImplementation implements DoctorService {

    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DrugRepository drugRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DoctorServiceImplementation(UserRepository userRepository, DiagnosisRepository diagnosisRepository,
                                     DrugRepository drugRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Doctor register(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        return null;
    }

    @Override
    public Doctor findById(Long id) {
        return null;
    }

    @Override
    public Doctor findByName(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
