package com.workintech.ecommerce.dto.response;

import com.workintech.ecommerce.dto.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse{
    private Long id;

    private Long addressId;

    private String orderDate;

    private String cardNumber;

    private String cardMonth;

    private String cardYear;

    private String cardCvv;

    private String price;

    private String userName;

    private List<ProductRequest> products;
}