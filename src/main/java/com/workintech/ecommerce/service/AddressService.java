package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {
    Address findById(Long id);
    List<Address> findAll();
    Address save(Address address);
    void delete(Address address);
}