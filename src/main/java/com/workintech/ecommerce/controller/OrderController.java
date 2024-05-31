package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.request.OrderRequest;
import com.workintech.ecommerce.dto.response.OrderResponse;
import com.workintech.ecommerce.service.OrderService;
import com.workintech.ecommerce.service.OrderedProductService;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, OrderedProductService orderedProductService,
                           ProductService productService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public OrderResponse saveOrder(@RequestBody OrderRequest order) {

        return orderService.save(order);
    }


}