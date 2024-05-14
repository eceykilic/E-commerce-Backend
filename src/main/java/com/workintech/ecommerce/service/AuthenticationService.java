package com.workintech.ecommerce.service;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.request.LoginRequest;
import com.workintech.ecommerce.dto.response.UserResponse;
import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.repository.RoleRepository;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.util.EcommerceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(String name, String email, String password, String role) {
        Role userRole = roleRepository.findByAuthority(role)
                .orElseThrow(() -> new EcommerceException("Role must not be empty", HttpStatus.BAD_REQUEST));

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(userRole);

        return userRepository.save(user);
    }

    public UserResponse login(LoginRequest loginRequest) {
        EcommerceValidation.checkEmptyOrNull(loginRequest.email(), "email");
        EcommerceValidation.checkEmptyOrNull(loginRequest.password(), "password");

        User user = userRepository.findUserByEmail(loginRequest.email())
                .orElseThrow(() -> new EcommerceException("Invalid Credentials", HttpStatus.BAD_REQUEST));

        if (passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            return Converter.findUser(user);
        } else {
            throw new EcommerceException("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
    }
}
