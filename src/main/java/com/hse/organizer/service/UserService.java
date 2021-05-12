package com.hse.organizer.service;

import com.hse.organizer.model.Diagnosis;
import com.hse.organizer.model.Drug;
import com.hse.organizer.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Service interface for class {@link User}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findById(Long id);

    User findByUsername(String name);

    void delete(Long id);

    List<Drug> getUserDrugs(Long userId);

    List<Diagnosis> getUserDiagnosis(Long userId);

    Boolean deleteFromMedKit(String barcode, String username);

    Boolean changePassword(String username, String password);

    Boolean assertPasswords(String password, String username);
}
