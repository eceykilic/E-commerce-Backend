package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM ecommerce.address AS a WHERE a.id = :id",nativeQuery = true)
    Optional<Address> findAddressByID(long id);
}
