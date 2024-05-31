package com.workintech.ecommerce.dto.response;

import java.util.List;


public record ProductResponse(Long id, String name, String description, Double price, Integer stock, Long categoryId,
                              Double rating, Integer sellCount, List<String> images) {
}
