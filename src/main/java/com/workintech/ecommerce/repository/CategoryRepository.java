package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM ecommerce.categories AS c WHERE c.category_id = :categoryID",nativeQuery = true)
    Category getCategoryByID(long categoryID);
}