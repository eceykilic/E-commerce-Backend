package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct,Long> {
}
