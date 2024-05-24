package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.response.UserResponse;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.AddressService;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/address")
    public Address saveAddress(@RequestBody Address address){
        return addressService.save(address);
    }

    @GetMapping("/address")
    public List<Address> findAllAddresses(){
        return addressService.findAll();
    }
}
