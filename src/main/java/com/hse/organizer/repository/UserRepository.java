package com.hse.organizer.repository;

import com.hse.organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User,Long> {
    User getById(Long id);

    User findByName(String name);
}
