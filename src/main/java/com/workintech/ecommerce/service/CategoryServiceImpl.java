package com.workintech.ecommerce.service;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.CategoryResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return Converter.findCategories(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }


    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
