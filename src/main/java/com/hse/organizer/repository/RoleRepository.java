package com.hse.organizer.repository;

import com.hse.organizer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
