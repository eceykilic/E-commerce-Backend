package com.workintech.ecommerce.dto.response;

public record ProductResponse(Long id, String name, String description, String price, String stock, Long categoryId,
                              String rating, String sellCount, String image) {
}
