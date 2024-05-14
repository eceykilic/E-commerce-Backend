package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.response.UserResponse;
import com.workintech.ecommerce.entity.User;

import java.util.List;

public interface UserService {
    void findByEmail(String email);
    User findById(Long id);
    List<UserResponse> getAllUsers();

    UserResponse save(User user);
    User delete(Long id);
}