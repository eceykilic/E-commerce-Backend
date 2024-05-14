package com.workintech.ecommerce.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {

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