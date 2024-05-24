package com.workintech.ecommerce.dto.response;

public record ProductResponse(Long id, String name, String description, Double price, Integer stock, Long categoryId,
                              Double rating, Integer sellCount, String image) {
}
