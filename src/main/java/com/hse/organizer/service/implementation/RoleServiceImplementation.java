package com.hse.organizer.service.implementation;

import com.hse.organizer.model.Role;
import com.hse.organizer.repository.RoleRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.RoleService;
import com.hse.organizer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link RoleService} interface.
 * Wrapper for {@link RoleRepository} + business logic.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Service
@Slf4j
public class RoleServiceImplementation implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
        log.info("IN addRole role: {} was saved successfully", role.toString());
    }
}
