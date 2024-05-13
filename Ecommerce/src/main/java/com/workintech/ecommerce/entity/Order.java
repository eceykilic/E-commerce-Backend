package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order", schema = "ecommerce")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address_id")
    private long addressId;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_month")
    private String cardMonth;

    @Column(name = "card_year")
    private String cardYear;

    @Column(name = "card_cvv")
    private String cardCvv;

    @Column(name = "price")
    private String price;

    @Column(name = "username")
    private String userName;

    @ManyToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "order_product", schema = "ecommerce", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Products> products;


}