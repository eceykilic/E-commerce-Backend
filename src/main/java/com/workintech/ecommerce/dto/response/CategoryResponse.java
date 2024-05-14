package com.workintech.ecommerce.dto.response;

import com.workintech.ecommerce.entity.Gender;

public record CategoryResponse(Long id, String code, String title, String img, Double rating, Gender gender){
}