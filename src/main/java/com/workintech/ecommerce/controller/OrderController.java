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
    private OrderService orderService;
    private OrderedProductService orderedProductService;
    private ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, OrderedProductService orderedProductService,
                           ProductService productService) {
        this.orderService = orderService;
        this.productService=productService;
        this.orderedProductService = orderedProductService;
    }

    @PostMapping("/")
    public OrderResponse saveOrder(@RequestBody OrderRequest order) {

        return orderService.save(order);
    }


}