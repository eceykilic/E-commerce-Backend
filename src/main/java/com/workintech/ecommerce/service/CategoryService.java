package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.response.CategoryResponse;
import com.workintech.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    Category findById(Long id);
    Category save(Category category);
    void saveAll(List<Category> categories);
    void delete(Long id);
}