package com.hse.organizer.service;

import com.hse.organizer.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findById(Long id);

    User findByName(String name);

    void delete(Long id);
}
