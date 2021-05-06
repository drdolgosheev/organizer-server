package com.hse.organizer.service.implementation;

import com.hse.organizer.model.*;
import com.hse.organizer.repository.DiagnosisRepository;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.RoleRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.UserService;
import com.hse.organizer.service.implementation.validator.EmailValidator;
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
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, DiagnosisRepository diagnosisRepository,
                                     DrugRepository drugRepository, BCryptPasswordEncoder passwordEncoder,
                                     RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Register user
     * @param user User info
     * @return user if registered successfully
     */
    @Override
    public User register(User user) {
        Date date = new Date();
        List<Drug> drugList = new ArrayList<>();
        List<Diagnosis> diagnosisList = new ArrayList<>();


        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDiagnosisList(diagnosisList);
        user.setMedKit(drugList);
        user.setStatus(Status.ACTIVE);
        user.setCreated(date);
        user.setUpdated(date);
        user.setRoleList(roleList);

        boolean isPasswordValid = true;
        boolean isEmailValid = EmailValidator.isValidEmailAddress(user.getEmail());
        if(isEmailValid && isPasswordValid) {
            User regUser = userRepository.save(user);
            log.info("IN register user was registered successfully: {}", regUser.toString());
            return regUser;
        }
        else {
            log.info("IN register user has incorrect log or pass");
            return null;
        }
    }

    /**
     * Get all registered users
     * @return List all of users in db
     */
    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();

        log.info("Users were found successfully IN getAll");

        return result;
    }

    /**
     * Get user by id
     * @param id user id
     * @return user
     */
    @Override
    public User findById(Long id) {
        User user = userRepository.getById(id);

        if (user == null)
            log.info("IN findById user with id: {} do not exist", id);
        else
            log.info("IN findById user was found successfully: {}", user.toString());

        return user;
    }

    /**
     * Get user by username
     * @param username user username
     * @return user
     */
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        log.info("IN findByEmail user was found successfully: {}", user.toString());

        return user;
    }

    /**
     * Delete user from db
     * @param id user ID
     */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete user with id: {} was deleted successfully", id);
    }

    /**
     * Return user med kit
     * @param userId user ID
     * @return List of drugs connected with user
     */
    @Override
    public List<Drug> getUserDrugs(Long userId) {
        User user = userRepository.getById(userId);

        if (userId == null) {
            log.info("IN getUserDrugs user with id: {} do not exist", userId);
        }

        return user.getMedKit();
    }

    /**
     * Gets user Diagnosis
     * @param userId user ID
     * @return List og user diagnosis
     */
    @Override
    public List<Diagnosis> getUserDiagnosis(Long userId) {
        User user = userRepository.getById(userId);

        if (userId == null) {
            log.info("IN getUserDiagnosis user with id: {} do not exist", userId);
        }

        return user.getDiagnosisList();
    }

    /**
     * Delete drug from user's med kit
     * @param barcode drug barcode
     * @param username username
     * @return false if, do not delete, true if deleted successfully
     */
    @Override
    public Boolean deleteFromMedKit(String barcode, String username) {
        Drug drug = drugRepository.findByBarcode(barcode);
        User user = userRepository.findByUsername(username);

        if(drug == null || user == null)
            return false;

        List<Drug> curDrugList = user.getMedKit();
        List<User> curUserList = drug.getUsers();

        for (int i = 0; i < curDrugList.size(); i++) {
            Drug locDrug = curDrugList.get(i);
            if(locDrug.getBarcode().equals(barcode)){
                curDrugList.remove(locDrug);
            }
        }

        for (int i = 0; i < curUserList.size(); i++) {
            User locUser = curUserList.get(i);
            if(locUser.getUsername().equals(username)){
                curUserList.remove(locUser);
            }
        }

        drug.setUsers(curUserList);
        user.setMedKit(curDrugList);

        drugRepository.save(drug);
        userRepository.save(user);

        return true;
    }
}
