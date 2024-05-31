package com.workintech.ecommerce.service;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.UserResponse;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.util.EcommerceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void findByEmail(String email) {
        boolean userExist = userRepository.findUserByEmail(email).isPresent();
        if (userExist) {
            throw new EcommerceException(EcommerceValidation.IS_EMAIL_PRESENT, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new EcommerceException(EcommerceValidation.IS_USER_PRESENT, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return Converter.findUsers(userRepository.findAll());
    }

    @Override
    public UserResponse save(User user) {

        EcommerceValidation.checkEmptyOrNull(user.getName(), "name");
        EcommerceValidation.checkEmptyOrNull(user.getEmail(), "email");
        EcommerceValidation.checkEmptyOrNull(user.getPassword(), "password");
        return Converter.findUser(userRepository.save(user));
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        EcommerceValidation.IsUserPresent(user);
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EcommerceException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new EcommerceException("user not found", HttpStatus.NOT_FOUND));
    }
}