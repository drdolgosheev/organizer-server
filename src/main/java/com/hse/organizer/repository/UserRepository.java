package com.hse.organizer.repository;

import com.hse.organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getById(Long id);

    User findByName(String name);
}
