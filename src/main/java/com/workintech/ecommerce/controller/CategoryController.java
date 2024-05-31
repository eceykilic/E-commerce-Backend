package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.response.CategoryResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;
    private static final String GET_ALL_CATEGORIES = "https://workintech-fe-ecommerce.onrender.com/categories";
    private final RestTemplate restTemplate;

    @Autowired
    public CategoryController(CategoryService categoryService, RestTemplateBuilder restTemplateBuilder) {
        this.categoryService = categoryService;
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public List<CategoryResponse> getAllCategories(){
        // Dış API'den kategorileri al
        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity(GET_ALL_CATEGORIES, Category[].class);
        Category[] categories = responseEntity.getBody();

        if (categories != null) {
            // Kategorileri kaydet
            categoryService.saveAll(List.of(categories));
        }

        // Tüm kategorileri döndür
        return categoryService.findAll();
    }

    @PostMapping("/")
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }
}
