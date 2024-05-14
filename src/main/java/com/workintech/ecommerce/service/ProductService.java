package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.response.ProductResponse;
import com.workintech.ecommerce.entity.Products;

import java.util.List;

public interface ProductService {

    Products saveProduct(Products product);
    List<ProductResponse> saveAll(List<Products> productsList);
    ProductResponse findByProductName(String name);
    List<Products> getProductsByCategoryId(Long categoryId);
    List<ProductResponse> searchByName(String name);
    List<ProductResponse> highestToLowestSorting();
    List<ProductResponse> lowestToHighestSorting();
    List<ProductResponse> worstToBestSorting();
    List<ProductResponse> bestToWorstSorting();
    List<ProductResponse> searchAndLowestSorting(String name);
    List<ProductResponse> searchAndHighestSorting(String name);
    List<ProductResponse> searchAndWorstSorting(String name);
    List<ProductResponse> searchAndBestSorting(String name);
    List<Products> getAllProducts();
    Products deleteProduct(Long id);
    Products getProductById(Long id);
    List<Products> searchByNameAndCategory(String name,Long categoryId);
    List<Products> searchAndWorstSortAndCategory(Long categoryId,String name);
    List<Products> searchAndBestSortAndCategory(Long categoryId,String name);
    List<Products> searchAndAscSortAndCategory(Long categoryId,String name);
    List<Products> searchAndDescSortAndCategory(Long categoryId,String name);
    List<Products> highestToLowestSortingAndCategory(Long categoryId);
    List<Products> lowestToHighestSortingAndCategory(Long categoryId);
    List<Products> worstToBestSortingAndCategory(Long categoryId);
    List<Products> bestToWorstSortingAndCategory(Long categoryId);
}