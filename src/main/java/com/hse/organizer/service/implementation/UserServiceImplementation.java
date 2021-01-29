package com.hse.organizer.service.implementation;

import com.hse.organizer.model.User;
import com.hse.organizer.repository.DiagnosisRepository;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
