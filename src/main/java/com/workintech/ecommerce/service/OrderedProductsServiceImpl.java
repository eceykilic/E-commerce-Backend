package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.OrderedProduct;
import com.workintech.ecommerce.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductsServiceImpl implements OrderedProductService{

    private final OrderedProductRepository orderedProductRepository;
    @Autowired
    public OrderedProductsServiceImpl(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }
    @Override
    public OrderedProduct saveOrderedProduct(OrderedProduct orderedProduct) {
        return orderedProductRepository.save(orderedProduct);
    }

    @Override
    public List<OrderedProduct> getAllProducts() {
        return orderedProductRepository.findAll();
    }
}