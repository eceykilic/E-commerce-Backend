package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.request.OrderRequest;
import com.workintech.ecommerce.dto.response.OrderResponse;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = new Order();
        order.setAddressId(orderRequest.getAddressId());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setCardNumber(orderRequest.getCardNumber());
        order.setCardMonth(orderRequest.getCardMonth());
        order.setCardYear(orderRequest.getCardYear());
        order.setCardCvv(orderRequest.getCardCvv());
        order.setPrice(orderRequest.getPrice());
        order.setUserName(orderRequest.getUserName());

        // Save the order to database
        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new EcommerceException("Error occurred while saving order", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Create OrderResponse
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setAddressId(order.getAddressId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setCardNumber(order.getCardNumber());
        orderResponse.setCardMonth(order.getCardMonth());
        orderResponse.setCardYear(order.getCardYear());
        orderResponse.setCardCvv(order.getCardCvv());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setUserName(order.getUserName());

        return orderResponse;
    }
}
