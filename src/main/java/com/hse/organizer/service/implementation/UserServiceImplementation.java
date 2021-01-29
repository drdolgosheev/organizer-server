package com.hse.organizer.service.implementation;

import com.hse.organizer.model.Diagnosis;
import com.hse.organizer.model.Drug;
import com.hse.organizer.model.Status;
import com.hse.organizer.model.User;
import com.hse.organizer.repository.DiagnosisRepository;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DrugRepository drugRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, DiagnosisRepository diagnosisRepository,
                                     DrugRepository drugRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Date date = new Date();
        List<Drug> drugList = new ArrayList<>();
        List<Diagnosis> diagnosisList = new ArrayList<>();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDiagnosisList(diagnosisList);
        user.setMedKit(drugList);
        user.setStatus(Status.ACTIVE);
        user.setCreated(date);

        User regUser = userRepository.save(user);

        log.info("IN register user was registered successfully: {}", regUser.toString());

        return regUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();

        log.info("Users were found successfully IN getAll");

        return result;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.getById(id);

        if (user == null) {
            log.info("IN findById user with id: {} do not exist", id);
        }

        log.info("IN findById user was found successfully: {}", user.toString());

        return user;
    }

    @Override
    public User findByName(String name) {
        User user = userRepository.findByName(name);

        log.info("IN findByName user was found successfully: {}", user.toString());

        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete user with id: {} was deleted successfully", id);
    }

    @Override
    public List<Drug> getUserDrugs(Long userId) {
        User user = userRepository.getById(userId);

        if (userId == null) {
            log.info("IN getUserDrugs user with id: {} do not exist", userId);
        }

        return user.getMedKit();
    }

    @Override
    public List<Diagnosis> getUserDiagnosis(Long userId) {
        User user = userRepository.getById(userId);

        if (userId == null) {
            log.info("IN getUserDiagnosis user with id: {} do not exist", userId);
        }

        return user.getDiagnosisList();
    }
}
