package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.response.ProductResponse;
import com.workintech.ecommerce.entity.Products;

import java.util.List;

public interface ProductService {

    Products saveProduct(Products product);
    List<ProductResponse> saveAll(List<Products> productsList);
    ProductResponse findByProductName(String name);
    List<Products> getProductsByCategoryId(Long categoryId);
    List<Products> getAllProducts();
    Products deleteProduct(Long id);
    Products getProductById(Long id);

}