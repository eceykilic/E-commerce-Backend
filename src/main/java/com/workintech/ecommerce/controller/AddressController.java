package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.AddressResponse;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public AddressResponse findById(@PathVariable Long id){
        return Converter.findAddress(addressService.findById(id));
    }

    @GetMapping("/all")
    public List<AddressResponse> findAll(){
        List<Address> allAddresses = addressService.findAll();
        return Converter.findAll(allAddresses);
    }

    @PutMapping("/{id}")
    public AddressResponse update(@RequestBody Address updatedAddress, @PathVariable Long id) {
        // Veritabanında güncellenecek adresi bul
        Address existingAddress = addressService.findById(id);

        if (existingAddress != null) {
            // Güncellenen adres bilgilerini mevcut adrese kopyala
            existingAddress.setTitle(updatedAddress.getTitle());
            existingAddress.setName(updatedAddress.getName());
            existingAddress.setSurname(updatedAddress.getSurname());
            existingAddress.setPhone(updatedAddress.getPhone());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setDistrict(updatedAddress.getDistrict());
            existingAddress.setNeighborhood(updatedAddress.getNeighborhood());
            existingAddress.setAddress(updatedAddress.getAddress());

            // Adresi güncelle
            Address savedAddress = addressService.save(existingAddress);

            // Güncellenen adresi DTO'ya dönüştürerek döndür
            return Converter.findAddress(savedAddress);
        } else {
            // Belirtilen ID'ye sahip adres bulunamadıysa uygun bir hata işleme
            throw new EcommerceException("Address not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // Veritabanında silinecek adresi bul
        Address existingAddress = addressService.findById(id);

        if (existingAddress != null) {
            // Adres bulundu, sil
            addressService.delete(existingAddress);
        } else {
            // Belirtilen ID'ye sahip adres bulunamadıysa uygun bir hata işleme
            throw new EcommerceException("Address not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public AddressResponse save(@RequestBody Address newAddress) {
        // Yeni adresi kaydet
        Address savedAddress = addressService.save(newAddress);

        // Kaydedilen adresi DTO'ya dönüştürerek döndür
        return Converter.findAddress(savedAddress);
    }



}