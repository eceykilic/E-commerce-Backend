package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order,Long> {
}