package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        // Get roles from the repository
        List<Role> roles = roleRepository.findAll();

        // Check if roles are present
        if (roles.isEmpty()) {
            throw new RuntimeException("No roles found in the database.");
        }

        return roles;
    }
}
