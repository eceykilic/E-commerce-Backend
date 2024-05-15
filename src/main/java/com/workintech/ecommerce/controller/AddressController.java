package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.request.AddressRequest;
import com.workintech.ecommerce.dto.response.AddressResponse;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> findById(@PathVariable Long id) {
        Address address = addressService.findById(id);
        if (address != null) {
            return ResponseEntity.ok(Converter.findAddress(address));
        } else {
            throw new EcommerceException("Address not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<AddressResponse> findAll() {
        List<Address> allAddresses = addressService.findAll();
        return Converter.findAll(allAddresses);
    }

    @PutMapping("/{id}")
    public AddressResponse update(@RequestBody AddressRequest updatedAddress, @PathVariable Long id) {
        Address existingAddress = addressService.findById(id);
        if (existingAddress != null) {
            existingAddress.setTitle(updatedAddress.title());
            existingAddress.setName(updatedAddress.name());
            existingAddress.setSurname(updatedAddress.surname());
            existingAddress.setPhone(updatedAddress.phone());
            existingAddress.setCity(updatedAddress.city());
            existingAddress.setDistrict(updatedAddress.district());
            existingAddress.setNeighborhood(updatedAddress.neighborhood());
            existingAddress.setAddress(updatedAddress.address());
            Address savedAddress = addressService.save(existingAddress);
            return Converter.findAddress(savedAddress);
        } else {
            throw new EcommerceException("Address not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Address existingAddress = addressService.findById(id);
        if (existingAddress != null) {
            addressService.delete(existingAddress);
            return ResponseEntity.noContent().build();
        } else {
            throw new EcommerceException("Address not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public AddressResponse save(@RequestBody AddressRequest newAddressRequest) {
        Address newAddress = new Address();
        newAddress.setTitle(newAddressRequest.title());
        newAddress.setName(newAddressRequest.name());
        newAddress.setSurname(newAddressRequest.surname());
        newAddress.setPhone(newAddressRequest.phone());
        newAddress.setCity(newAddressRequest.city());
        newAddress.setDistrict(newAddressRequest.district());
        newAddress.setNeighborhood(newAddressRequest.neighborhood());
        newAddress.setAddress(newAddressRequest.address());
        Address savedAddress = addressService.save(newAddress);
        return Converter.findAddress(savedAddress);
    }
}
