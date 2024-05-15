package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.service.AddressService;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public Address save(@RequestBody Address address){
        return addressService.save(address);
    }

    @GetMapping("/address")
    public List<Address> findAll(){
        return addressService.findAll();
    }
}
