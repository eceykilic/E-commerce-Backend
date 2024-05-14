package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.request.OrderRequest;
import com.workintech.ecommerce.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse save(OrderRequest orderRequest);

}