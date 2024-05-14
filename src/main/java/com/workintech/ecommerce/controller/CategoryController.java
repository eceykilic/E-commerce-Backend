package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.response.CategoryResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/")
    public List<CategoryResponse> getAllCategories(){
        return categoryService.findAll();
    }

    @PostMapping("/")
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

}